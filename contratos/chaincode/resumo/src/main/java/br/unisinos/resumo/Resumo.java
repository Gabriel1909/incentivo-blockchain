package br.unisinos.resumo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@DataType
@Getter
@ToString
@EqualsAndHashCode
public class Resumo {

    @Property
    private final Map<String, Set<String>> relacionamentos;

    @Property
    private String dataAlteracao;

    public Resumo() {
        this.relacionamentos = new HashMap<>();
    }

    public void adicionarRelacionamento(String idRelacionamento, String idRegistro) {

        Set<String> registros = this.relacionamentos.getOrDefault(idRelacionamento, new HashSet<>());
        registros.add(idRegistro);

        this.relacionamentos.put(idRelacionamento, registros);
        this.dataAlteracao = Instant.now().toString();
    }
}
