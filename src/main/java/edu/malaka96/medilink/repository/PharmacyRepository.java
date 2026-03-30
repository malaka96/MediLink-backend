package edu.malaka96.medilink.repository;

import edu.malaka96.medilink.model.entity.PharmacyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<PharmacyEntity, Long> {
    boolean existsByName(String name);
}