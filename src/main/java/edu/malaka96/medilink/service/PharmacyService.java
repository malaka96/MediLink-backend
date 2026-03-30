package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.PharmacyRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyResponseDto;

public interface PharmacyService {
    PharmacyResponseDto createPharmacy(PharmacyRequestDto pharmacyRequestDto);
}