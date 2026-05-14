package edu.malaka96.medilink.repository;

import edu.malaka96.medilink.model.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    boolean existsByMedicineIdAndPharmacyBranchId(Long medicineId, Long branchId);
}