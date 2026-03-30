package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.PharmacyAlreadyExistsException;
import edu.malaka96.medilink.exception.UserNotFoundException;
import edu.malaka96.medilink.model.dto.PharmacyRequestDto;
import edu.malaka96.medilink.model.dto.PharmacyResponseDto;
import edu.malaka96.medilink.model.entity.PharmacyEntity;
import edu.malaka96.medilink.model.entity.UserEntity;
import edu.malaka96.medilink.repository.PharmacyRepository;
import edu.malaka96.medilink.repository.UserRepository;
import edu.malaka96.medilink.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;

    @Override
    public PharmacyResponseDto createPharmacy(PharmacyRequestDto pharmacyRequestDto) {
        if (pharmacyRepository.existsByName(pharmacyRequestDto.getName())) {
            throw new PharmacyAlreadyExistsException("Pharmacy with name '" + pharmacyRequestDto.getName() + "' already exists");
        }
        return mapToResponseDto(pharmacyRepository.save(mapToEntity(pharmacyRequestDto)));
    }

    private PharmacyEntity mapToEntity(PharmacyRequestDto pharmacyRequestDto) {
        UserEntity owner = userRepository.findByEmail(pharmacyRequestDto.getOwnerEmail())
                .orElseThrow(() -> new UserNotFoundException("User with email '" + pharmacyRequestDto.getOwnerEmail() + "' not found"));

        return PharmacyEntity.builder()
                .name(pharmacyRequestDto.getName())
                .owner(owner)
                .build();
    }

    private PharmacyResponseDto mapToResponseDto(PharmacyEntity pharmacyEntity) {
        PharmacyResponseDto responseDto = new PharmacyResponseDto();
        responseDto.setId(pharmacyEntity.getId());
        responseDto.setName(pharmacyEntity.getName());
        responseDto.setOwnerEmail(pharmacyEntity.getOwner().getEmail());
        responseDto.setCreatedAt(pharmacyEntity.getCreatedAt());
        return responseDto;
    }
}