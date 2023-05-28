package br.unisinos.blockchain.repository;

import br.unisinos.blockchain.domain.mimic.Admissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionsRepository extends JpaRepository<Admissions, Integer> {
    List<Admissions> findFirst10ByAdmitProviderId(String admitProviderId);
}