package br.unisinos.blockchain.service;

import br.unisinos.blockchain.domain.RegistroSaude;
import br.unisinos.blockchain.domain.mimic.Admissions;
import br.unisinos.blockchain.repository.AdmissionsRepository;
import br.unisinos.blockchain.repository.LabEeventsRepository;
import br.unisinos.blockchain.repository.MicrobiologyEventsRepository;
import br.unisinos.blockchain.repository.PrescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EhrService {

    @Autowired
    private AdmissionsRepository admissionsRepository;

    @Autowired
    private MicrobiologyEventsRepository microbiologyEventsRepository;

    @Autowired
    private LabEeventsRepository labEeventsRepository;

    @Autowired
    private PrescriptionsRepository prescriptionsRepository;

    public List<RegistroSaude> buscarAdmissoes(String providerId) {
        List<Admissions> admissoes = admissionsRepository.findFirst10ByAdmitProviderId(providerId);

        return admissoes.stream()
                .map(this::mapearRegistro)
                .toList();
    }

    private RegistroSaude mapearRegistro(Admissions admissao) {
        return new RegistroSaude(admissao.getSubjectId(), admissao.getAdmitProviderId(), admissao.getHadmId(), calcularSignificanciaRegistro(admissao.getHadmId()));
    }

    private double calcularSignificanciaRegistro(Integer hadmId) {
        int significancia = 1;

        significancia += microbiologyEventsRepository.countByHadmId(hadmId);
//        significancia += labEeventsRepository.countByHadmId(hadmId);
        significancia += prescriptionsRepository.countByHadmId(hadmId);

        return significancia;
    }
}
