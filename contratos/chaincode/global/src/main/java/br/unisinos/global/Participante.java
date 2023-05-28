package br.unisinos.global;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Participante {

    @Property
    private final Funcao funcao;

    @Property
    private double significancia;

    public void adicionarSignificancia(double significancia) {
        this.significancia += significancia;
    }
}
