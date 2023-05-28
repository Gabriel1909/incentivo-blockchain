package br.unisinos.global;

import lombok.*;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Getter
@AllArgsConstructor
public class Global {

    @Property
    private final String validador;

    @Property
    private final double significanciaTotal;
}
