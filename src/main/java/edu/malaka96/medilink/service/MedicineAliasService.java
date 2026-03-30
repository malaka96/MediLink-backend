package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.MedicineAliasRequestDto;
import edu.malaka96.medilink.model.dto.MedicineAliasResponseDto;

public interface MedicineAliasService {
    MedicineAliasResponseDto createAlias(MedicineAliasRequestDto medicineAliasRequestDto);
}