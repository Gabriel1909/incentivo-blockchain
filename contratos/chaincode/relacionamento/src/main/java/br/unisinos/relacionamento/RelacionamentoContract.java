package br.unisinos.relacionamento;

import com.google.gson.Gson;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;

import java.time.Instant;

import static com.google.common.collect.Lists.newArrayList;

@Contract(
        name = "relacionamento",
        info = @Info(
                title = "Contrato de Relacionamento",
                description = "Contrato para relacionamento entre um provedor e um paciente na rede",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "gabriel28@edu.unisinos.br",
                        name = "Gabriel Bittencourt",
                        url = "https://hyperledger.example.com")))
@Default
public class RelacionamentoContract implements ContractInterface {

    private final Gson gson = new Gson();

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public Relacionamento adicionarRelacionamento(final Context ctx, String idPaciente, String idProvedor, String idRegistro, double significancia) {

        String registroExistente = ctx.getStub().getStringState(idRegistro);

        if (!StringUtil.isNullOrEmpty(registroExistente)){
            return atualizarRelacionamentoExistente(ctx, idPaciente, idProvedor, idRegistro, significancia, registroExistente);
        }

        Relacionamento relacionamento = new Relacionamento(idPaciente, idProvedor, idRegistro, Instant.now().toString(), significancia);

        ctx.getStub().invokeChaincodeWithStringArgs("resumo", newArrayList("adicionarRelacionamentoNosResumos", idPaciente, idProvedor, idRegistro, String.valueOf(significancia)));

        ctx.getStub().putStringState(idRegistro, gson.toJson(relacionamento));
        return relacionamento;
    }

    private Relacionamento atualizarRelacionamentoExistente(Context ctx, String idPaciente, String idProvedor, String idRegistro, double novaSignificancia, String registroExistente) {
        Relacionamento relacionamento = gson.fromJson(registroExistente, Relacionamento.class);

        double significanciaAtual = relacionamento.getSignificancia();

        if (significanciaAtual != novaSignificancia) {
            relacionamento.setSignificancia(novaSignificancia);

            ctx.getStub().invokeChaincodeWithStringArgs("resumo", newArrayList("adicionarRelacionamentoNosResumos",
                    idPaciente, idProvedor, idRegistro, String.valueOf(novaSignificancia - significanciaAtual)));

            ctx.getStub().putStringState(idRegistro, gson.toJson(relacionamento));
        }

        return relacionamento;
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Relacionamento buscarRelacionamento(final Context ctx, String idRegistro) {
        return gson.fromJson(ctx.getStub().getStringState(idRegistro), Relacionamento.class);
    }
}
