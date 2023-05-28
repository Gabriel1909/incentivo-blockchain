package br.unisinos.global;

import com.google.gson.Gson;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ledger.KeyValue;

import java.util.HashMap;
import java.util.Map;

@Contract(
        name = "global",
        info = @Info(
                title = "Contrato Global",
                description = "Contrato Global para gerenciamento da rede",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "gabriel28@edu.unisinos.br",
                        name = "Gabriel Bittencourt",
                        url = "https://hyperledger.example.com")))
@Default
public class GlobalContract implements ContractInterface {

    private final Gson gson = new Gson();

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void adicionarParticipante(final Context ctx, String idParticipante, String funcao) {

        Participante participante = new Participante(Funcao.valueOf(funcao), 0.0);

        ctx.getStub().putStringState(idParticipante, gson.toJson(participante));
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Participante buscarParticipante(final Context ctx, String idParticipante) {
        return gson.fromJson(ctx.getStub().getStringState(idParticipante), Participante.class);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Map<String, Participante> buscarParticipantes(final Context ctx) {

        Map<String, Participante> participantes = new HashMap<>();

        for (KeyValue keyValue : ctx.getStub().getStateByRange("", "")) {
            participantes.put(keyValue.getKey(), gson.fromJson(keyValue.getStringValue(), Participante.class));
        }

        return participantes;
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void adicionarSignificancia(final Context ctx, String idParticipante, double significancia) {
        Participante participante = gson.fromJson(ctx.getStub().getStringState(idParticipante), Participante.class);

        participante.adicionarSignificancia(significancia);

        ctx.getStub().putStringState(idParticipante, gson.toJson(participante));
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Global buscarGlobal(final Context ctx) {
        String peerValidador = "";
        double menorSignificancia = Double.MAX_VALUE;
        double significanciaTotal = 0;

        for (KeyValue keyValue : ctx.getStub().getStateByRange("", "")) {
            Participante participante = gson.fromJson(keyValue.getStringValue(), Participante.class);
            significanciaTotal += participante.getSignificancia();

            if (participante.getFuncao() == Funcao.PROVEDOR && participante.getSignificancia() < menorSignificancia) {
                peerValidador = keyValue.getKey();
                menorSignificancia = participante.getSignificancia();
            }
        }

        return new Global(peerValidador, significanciaTotal);
    }
}
