package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.MedicineRequestDto;
import edu.malaka96.medilink.model.dto.MedicineResponseDto;

public interface MedicineService {
    MedicineResponseDto createMedicine(MedicineRequestDto medicineRequestDto);
}