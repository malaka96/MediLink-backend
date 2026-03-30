package edu.malaka96.medilink.repository;

import edu.malaka96.medilink.model.entity.MedicineAliasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineAliasRepository extends JpaRepository<MedicineAliasEntity, Long> {
    boolean existsByAliasNameAndMedicineId(String aliasName, Long medicineId);
}