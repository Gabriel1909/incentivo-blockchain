package br.unisinos.blockchain.repository;

import br.unisinos.blockchain.domain.mimic.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescriptions, Integer> {
    int countByHadmId(Integer hadmId);
}