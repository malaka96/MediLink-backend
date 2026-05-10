package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.PharmacyBranchRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyBranchResponseDto;

import java.util.List;

public interface PharmacyBranchService {
    PharmacyBranchResponseDto createBranch(PharmacyBranchRequestDto pharmacyBranchRequestDto);
    List<PharmacyBranchResponseDto> getBranchesByOwnerEmail(String email);
}