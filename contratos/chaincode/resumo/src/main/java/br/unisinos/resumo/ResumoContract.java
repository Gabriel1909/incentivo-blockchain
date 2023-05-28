package br.unisinos.resumo;

import com.google.gson.Gson;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;

import static com.google.common.collect.Lists.newArrayList;

@Contract(
        name = "resumo",
        info = @Info(
                title = "Contrato de Resumo",
                description = "Contrato para resumo de um participante da rede",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "gabriel28@edu.unisinos.br",
                        name = "Gabriel Bittencourt",
                        url = "https://hyperledger.example.com")))
@Default
public class ResumoContract implements ContractInterface {

    private final Gson gson = new Gson();

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void adicionarRelacionamentoNosResumos(final Context ctx, String idPaciente, String idProvedor, String idRegistro, double significancia) {
        adicionarParticipante(ctx, idPaciente, idProvedor, idRegistro, "PACIENTE");
        adicionarParticipante(ctx, idProvedor, idPaciente, idRegistro, "PROVEDOR");
        ctx.getStub().invokeChaincodeWithStringArgs("global", newArrayList("adicionarSignificancia", idProvedor, String.valueOf(significancia)));
    }

    private void adicionarParticipante(Context ctx, String idResumo, String idRelacionamento, String idRegistro, String funcao) {
        Resumo resumo = buscarOuCriarResumo(ctx, idResumo, funcao);

        resumo.adicionarRelacionamento(idRelacionamento, idRegistro);
        ctx.getStub().putStringState(idResumo, gson.toJson(resumo));
    }

    private Resumo buscarOuCriarResumo(Context ctx, String idResumo, String funcao) {
        String resumoParticipanteJson = ctx.getStub().getStringState(idResumo);

        if (StringUtil.isNullOrEmpty(resumoParticipanteJson)) {
            if ("PACIENTE".equals(funcao)) {
                ctx.getStub().invokeChaincodeWithStringArgs("global", newArrayList("adicionarParticipante", idResumo, funcao));
            }

            return new Resumo();
        }

        return gson.fromJson(resumoParticipanteJson, Resumo.class);
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public Resumo buscarResumo(final Context ctx, String idResumo) {
        return gson.fromJson(ctx.getStub().getStringState(idResumo), Resumo.class);
    }
}
