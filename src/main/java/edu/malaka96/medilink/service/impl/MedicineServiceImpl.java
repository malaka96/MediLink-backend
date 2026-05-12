package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.MedicineAlreadyExistsException;
import edu.malaka96.medilink.exception.PharmacyBranchNotFoundException;
import edu.malaka96.medilink.exception.PharmacyNotFoundForUserException;
import edu.malaka96.medilink.exception.UnauthorizedBranchAccessException;
import edu.malaka96.medilink.model.dto.MedicineRequestDto;
import edu.malaka96.medilink.model.dto.MedicineResponseDto;
import edu.malaka96.medilink.model.entity.InventoryEntity;
import edu.malaka96.medilink.model.entity.MedicineEntity;
import edu.malaka96.medilink.model.entity.PharmacyBranch;
import edu.malaka96.medilink.repository.InventoryRepository;
import edu.malaka96.medilink.repository.MedicineRepository;
import edu.malaka96.medilink.repository.PharmacyBranchRepository;
import edu.malaka96.medilink.repository.PharmacyRepository;
import edu.malaka96.medilink.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final PharmacyBranchRepository pharmacyBranchRepository;
    private final PharmacyRepository pharmacyRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public MedicineResponseDto createMedicine(MedicineRequestDto dto, String email) {
        validateBranchOwnership(dto.getBranchId(), email);

        if (medicineRepository.existsByBrandNameAndDosage(dto.getBrandName(), dto.getDosage())) {
            throw new MedicineAlreadyExistsException("Medicine '" + dto.getBrandName() + "' with dosage '" + dto.getDosage() + "' already exists");
        }

        MedicineEntity savedMedicine = medicineRepository.save(mapToEntity(dto));

        PharmacyBranch branch = pharmacyBranchRepository.findById(dto.getBranchId()).get();
        inventoryRepository.save(InventoryEntity.builder()
                .medicine(savedMedicine)
                .pharmacyBranch(branch)
                .quantity(0)
                .build());

        return mapToResponseDto(savedMedicine, branch.getId());
    }

    private void validateBranchOwnership(Long branchId, String email) {
        PharmacyBranch branch = pharmacyBranchRepository.findById(branchId)
                .orElseThrow(() -> new PharmacyBranchNotFoundException("Branch with id " + branchId + " not found in the system"));

        pharmacyRepository.findByOwnerEmail(email)
                .filter(pharmacy -> pharmacy.getId().equals(branch.getPharmacyEntity().getId()))
                .orElseThrow(() -> new UnauthorizedBranchAccessException("Branch does not belong to your pharmacy"));
    }

    private MedicineEntity mapToEntity(MedicineRequestDto dto) {
        return MedicineEntity.builder()
                .brandName(dto.getBrandName())
                .genericName(dto.getGenericName())
                .dosage(dto.getDosage())
                .form(dto.getForm())
                .manufacturer(dto.getManufacturer())
                .description(dto.getDescription())
                .build();
    }

    private MedicineResponseDto mapToResponseDto(MedicineEntity medicineEntity, Long branchId) {
        MedicineResponseDto responseDto = new MedicineResponseDto();
        responseDto.setId(medicineEntity.getId());
        responseDto.setBranchId(branchId);
        responseDto.setBrandName(medicineEntity.getBrandName());
        responseDto.setGenericName(medicineEntity.getGenericName());
        responseDto.setDosage(medicineEntity.getDosage());
        responseDto.setForm(medicineEntity.getForm());
        responseDto.setManufacturer(medicineEntity.getManufacturer());
        responseDto.setDescription(medicineEntity.getDescription());
        return responseDto;
    }
}