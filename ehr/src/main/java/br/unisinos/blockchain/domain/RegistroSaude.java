package br.unisinos.blockchain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroSaude {

    private Integer subjectId;
    private String providerId;
    private Integer admId;
    private double significancia;
}
