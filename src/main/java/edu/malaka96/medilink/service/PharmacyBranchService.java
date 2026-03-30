package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.PharmacyBranchRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyBranchResponseDto;

public interface PharmacyBranchService {
    PharmacyBranchResponseDto createBranch(PharmacyBranchRequestDto pharmacyBranchRequestDto);
}