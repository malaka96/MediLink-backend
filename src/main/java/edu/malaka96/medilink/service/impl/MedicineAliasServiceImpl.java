package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.MedicineAliasAlreadyExistsException;
import edu.malaka96.medilink.exception.MedicineNotFoundException;
import edu.malaka96.medilink.model.dto.MedicineAliasRequestDto;
import edu.malaka96.medilink.model.dto.MedicineAliasResponseDto;
import edu.malaka96.medilink.model.entity.MedicineAliasEntity;
import edu.malaka96.medilink.model.entity.MedicineEntity;
import edu.malaka96.medilink.repository.MedicineAliasRepository;
import edu.malaka96.medilink.repository.MedicineRepository;
import edu.malaka96.medilink.service.MedicineAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineAliasServiceImpl implements MedicineAliasService {

    private final MedicineAliasRepository medicineAliasRepository;
    private final MedicineRepository medicineRepository;

    @Override
    public MedicineAliasResponseDto createAlias(MedicineAliasRequestDto medicineAliasRequestDto) {
        if (medicineAliasRepository.existsByAliasNameAndMedicineId(medicineAliasRequestDto.getAliasName(), medicineAliasRequestDto.getMedicineId())) {
            throw new MedicineAliasAlreadyExistsException("Alias '" + medicineAliasRequestDto.getAliasName() + "' already exists for this medicine");
        }
        return mapToResponseDto(medicineAliasRepository.save(mapToEntity(medicineAliasRequestDto)));
    }

    private MedicineAliasEntity mapToEntity(MedicineAliasRequestDto dto) {
        MedicineEntity medicine = medicineRepository.findById(dto.getMedicineId())
                .orElseThrow(() -> new MedicineNotFoundException("Medicine with id " + dto.getMedicineId() + " not found"));

        return MedicineAliasEntity.builder()
                .medicine(medicine)
                .aliasName(dto.getAliasName())
                .build();
    }

    private MedicineAliasResponseDto mapToResponseDto(MedicineAliasEntity medicineAliasEntity) {
        MedicineAliasResponseDto responseDto = new MedicineAliasResponseDto();
        responseDto.setId(medicineAliasEntity.getId());
        responseDto.setMedicineId(medicineAliasEntity.getMedicine().getId());
        responseDto.setAliasName(medicineAliasEntity.getAliasName());
        return responseDto;
    }
}