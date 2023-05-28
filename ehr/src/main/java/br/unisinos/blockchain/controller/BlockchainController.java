package br.unisinos.blockchain.controller;

import br.unisinos.blockchain.domain.RegistroSaude;
import br.unisinos.blockchain.service.ContratoService;
import br.unisinos.blockchain.service.EhrService;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static br.unisinos.blockchain.domain.Constants.ADICIONAR_RELACIONAMENTO;
import static br.unisinos.blockchain.domain.Constants.RELACIONAMENTO;

@RestController
public class BlockchainController {

    @Autowired
    private EhrService ehrService;

    @Autowired
    private ContratoService contratoService;

    @PostMapping("/admissoes/{providerId}")
    public List<RegistroSaude> salvarAdmissoes(@PathVariable String providerId) throws Exception {
        List<RegistroSaude> admissoes = ehrService.buscarAdmissoes(providerId);

        for (RegistroSaude admissao : admissoes) {
            String[] args = {String.valueOf(admissao.getSubjectId()), admissao.getProviderId(), String.valueOf(admissao.getAdmId()), String.valueOf(admissao.getSignificancia())};

            contratoService.submit(RELACIONAMENTO, ADICIONAR_RELACIONAMENTO, args);
        }

        return admissoes;
    }

    @PostMapping("/blockchain/evaluate/{chaincode}/{metodo}")
    public Object chamarContratoEvaluate(@RequestBody Map<String, String> args, @PathVariable String chaincode, @PathVariable String metodo) throws Exception {
        return contratoService.evaluate(chaincode, metodo, args.values().toArray(new String[0]));
    }

    @PostMapping("/blockchain/submit/{chaincode}/{metodo}")
    public Object chamarContratoSubmit(@RequestBody Map<String, String> args, @PathVariable String chaincode, @PathVariable String metodo) throws Exception {
        return contratoService.submit(chaincode, metodo, args.values().toArray(new String[0]));
    }
}
