package edu.malaka96.medilink.repository;

import edu.malaka96.medilink.model.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {
    boolean existsByBrandNameAndDosage(String brandName, String dosage);
}