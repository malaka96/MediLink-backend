package edu.malaka96.medilink.repository;

import edu.malaka96.medilink.model.entity.PharmacyBranch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyBranchRepository extends JpaRepository<PharmacyBranch, Long> {
    boolean existsByNameAndPharmacyEntityId(String name, Long pharmacyId);
}