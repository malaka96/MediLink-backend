package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.MedicineAlreadyExistsException;
import edu.malaka96.medilink.model.dto.MedicineRequestDto;
import edu.malaka96.medilink.model.dto.MedicineResponseDto;
import edu.malaka96.medilink.model.entity.MedicineEntity;
import edu.malaka96.medilink.repository.MedicineRepository;
import edu.malaka96.medilink.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public MedicineResponseDto createMedicine(MedicineRequestDto medicineRequestDto) {
        if (medicineRepository.existsByBrandNameAndDosage(
                medicineRequestDto.getBrandName(),
                medicineRequestDto.getDosage())) {
            throw new MedicineAlreadyExistsException("Medicine '"
                    + medicineRequestDto.getBrandName() + "' with dosage '"
                    + medicineRequestDto.getDosage() + "' already exists");
        }
        return mapToResponseDto(medicineRepository.save(mapToEntity(medicineRequestDto)));
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

    private MedicineResponseDto mapToResponseDto(MedicineEntity medicineEntity) {
        MedicineResponseDto responseDto = new MedicineResponseDto();
        responseDto.setId(medicineEntity.getId());
        responseDto.setBrandName(medicineEntity.getBrandName());
        responseDto.setGenericName(medicineEntity.getGenericName());
        responseDto.setDosage(medicineEntity.getDosage());
        responseDto.setForm(medicineEntity.getForm());
        responseDto.setManufacturer(medicineEntity.getManufacturer());
        responseDto.setDescription(medicineEntity.getDescription());
        return responseDto;
    }
}