package br.unisinos.blockchain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hyperledger.fabric.sdk.Peer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Global {

    private String validador;
    private Peer peerValidador;
    private double significanciaTotal;
}
