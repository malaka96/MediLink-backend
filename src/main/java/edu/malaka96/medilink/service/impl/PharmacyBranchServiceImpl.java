package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.PharmacyBranchAlreadyExistsException;
import edu.malaka96.medilink.exception.PharmacyNotFoundException;
import edu.malaka96.medilink.exception.UnauthorizedPharmacyAccessException;
import edu.malaka96.medilink.model.dto.PharmacyBranchRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyBranchResponseDto;
import edu.malaka96.medilink.model.entity.PharmacyBranch;
import edu.malaka96.medilink.model.entity.PharmacyEntity;
import edu.malaka96.medilink.repository.PharmacyBranchRepository;
import edu.malaka96.medilink.repository.PharmacyRepository;
import edu.malaka96.medilink.service.PharmacyBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyBranchServiceImpl implements PharmacyBranchService {

    private final PharmacyBranchRepository pharmacyBranchRepository;
    private final PharmacyRepository pharmacyRepository;

    @Override
    public PharmacyBranchResponseDto createBranch(PharmacyBranchRequestDto pharmacyBranchRequestDto, String email) {
        PharmacyEntity pharmacy = pharmacyRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new PharmacyNotFoundException("No pharmacy found for user: " + email));

        if (!pharmacy.getId().equals(pharmacyBranchRequestDto.getPharmacyId())) {
            throw new UnauthorizedPharmacyAccessException("Pharmacy does not belong to the authenticated user");
        }

        if (pharmacyBranchRepository.existsByNameAndPharmacyEntityId(pharmacyBranchRequestDto.getName(), pharmacy.getId())) {
            throw new PharmacyBranchAlreadyExistsException("Branch with name '"
                    + pharmacyBranchRequestDto.getName() + "' already exists in this pharmacy");
        }
        return mapToResponseDto(pharmacyBranchRepository.save(mapToEntity(pharmacyBranchRequestDto, pharmacy)));
    }

    @Override
    public List<PharmacyBranchResponseDto> getBranchesByOwnerEmail(String email) {
        PharmacyEntity pharmacy = pharmacyRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new PharmacyNotFoundException("No pharmacy found for user: " + email));
        return pharmacyBranchRepository.findByPharmacyEntityId(pharmacy.getId())
                .stream().map(this::mapToResponseDto).toList();
    }

    private PharmacyBranch mapToEntity(PharmacyBranchRequestDto dto, PharmacyEntity pharmacy) {
        return PharmacyBranch.builder()
                .pharmacyEntity(pharmacy)
                .name(dto.getName())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .contactNumber(dto.getContactNumber())
                .build();
    }

    private PharmacyBranchResponseDto mapToResponseDto(PharmacyBranch pharmacyBranch) {
        PharmacyBranchResponseDto responseDto = new PharmacyBranchResponseDto();
        responseDto.setId(pharmacyBranch.getId());
        responseDto.setPharmacyId(pharmacyBranch.getPharmacyEntity().getId());
        responseDto.setName(pharmacyBranch.getName());
        responseDto.setAddress(pharmacyBranch.getAddress());
        responseDto.setLatitude(pharmacyBranch.getLatitude());
        responseDto.setLongitude(pharmacyBranch.getLongitude());
        responseDto.setContactNumber(pharmacyBranch.getContactNumber());
        return responseDto;
    }
}