package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.MedicineRequestDto;
import edu.malaka96.medilink.model.dto.MedicineResponseDto;

import java.util.List;

public interface MedicineService {
    MedicineResponseDto createMedicine(MedicineRequestDto medicineRequestDto, String email);
    List<MedicineResponseDto> getMyPharmacyMedicines(String email);
}