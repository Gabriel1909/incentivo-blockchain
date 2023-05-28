package br.unisinos.blockchain.repository;

import br.unisinos.blockchain.domain.mimic.LabEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabEeventsRepository extends JpaRepository<LabEvents, Integer> {
    int countByHadmId(Integer hadmId);
}