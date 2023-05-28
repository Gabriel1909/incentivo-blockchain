package br.unisinos.blockchain.service;

import br.unisinos.blockchain.domain.Global;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static br.unisinos.blockchain.domain.Constants.*;
import static com.google.common.collect.Lists.newArrayList;

@Component
@Slf4j
public class ContratoService {

    @Autowired
    private Gson gson;

    @Autowired(required = false)
    @Getter
    private Network network;

    public Object evaluate(String chaincode, String metodo, String... args) throws ContractException {
        return evaluate(chaincode, metodo, Object.class, args);
    }

    public <T> T evaluate(String chaincode, String metodo, Class<T> classeRetorno, String... args) throws ContractException {
        byte[] response = network.getContract(chaincode).evaluateTransaction(metodo, args);

        return convert(response, classeRetorno);
    }

    public Object submit(String chaincode, String metodo, String... args) throws ContractException, InterruptedException, TimeoutException {

        Global global = selecionarValidador();

        byte[] response = network.getContract(chaincode)
                .createTransaction(metodo)
                .setEndorsingPeers(newArrayList(global.getPeerValidador()))
                .submit(args);

        if (!ADICIONAR_SIGNIFICANCIA.equals(metodo)) {

            double significanciaAdicional = Math.log10(global.getSignificanciaTotal()) * FRACAO_SIGNIFICANCIA; //TODO testar inicio de provedor com 0

            log.info("Validador: {}, Significancia total: {}, nova: {}", global.getValidador(), global.getSignificanciaTotal(), significanciaAdicional);
            submit(GLOBAL, ADICIONAR_SIGNIFICANCIA, global.getValidador(), String.valueOf(significanciaAdicional));
        }

        return convert(response, Object.class);
    }

    private <T> T convert(byte[] response, Class<T> classeRetorno) {
        try {
            return gson.fromJson(new String(response), classeRetorno);
        } catch (Exception e) {
            return (T) response;
        }
    }

    public Global selecionarValidador() throws ContractException {

        List<Peer> peers = network.getChannel().getPeers().stream().toList();

        Global global = evaluate(GLOBAL, BUSCAR_GLOBAL, Global.class);

        if (ObjectUtils.isEmpty(global.getValidador())) {
            return new Global(peers.get(0).getName(), peers.get(0), SIGNIFICANCIA_INICIAL);
        }

        peers.stream()
                .filter(peer -> peer.getName().equals(global.getValidador()))
                .forEach(global::setPeerValidador);

        return global;
    }
}
