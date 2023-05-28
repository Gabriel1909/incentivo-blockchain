package br.unisinos.blockchain.service;

import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static br.unisinos.blockchain.domain.Constants.*;

@Service
@Slf4j
public class InitService {

    @Autowired
    private ContratoService contratoService;

    @PostConstruct
    public void adicionarPeersNoChaincode() {
        for (Peer peer : contratoService.getNetwork().getChannel().getPeers()) {
            try {
                if (contratoService.evaluate(GLOBAL, BUSCAR_PARTICIPANTE, peer.getName()) == null) {
                    contratoService.submit(GLOBAL, ADICIONAR_PARTICIPANTE, peer.getName(), PROVEDOR);
                    log.info("Peer instanciado com sucesso: {}", peer.getName());
                }

            } catch (Exception e) {
                log.error("Erro ao instanciar peer: {}", peer.getName());
            }
        }
    }
}
