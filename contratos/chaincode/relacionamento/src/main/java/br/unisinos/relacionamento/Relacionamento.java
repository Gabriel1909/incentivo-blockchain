package br.unisinos.relacionamento;

import lombok.*;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Relacionamento {

    @Property
    private final String idPaciente;

    @Property
    private final String idProvedor;

    @Property
    private final String idRegistro;

    @Property
    private final String dataAlteracao;

    @Property
    @Setter
    private double significancia;
}
