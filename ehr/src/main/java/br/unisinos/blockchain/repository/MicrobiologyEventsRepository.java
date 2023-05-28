package br.unisinos.blockchain.repository;

import br.unisinos.blockchain.domain.mimic.MicrobiologyEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicrobiologyEventsRepository extends JpaRepository<MicrobiologyEvents, Integer> {
    int countByHadmId(Integer hadmId);
}