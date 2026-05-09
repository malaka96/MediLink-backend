package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.PharmacyAlreadyExistsException;
import edu.malaka96.medilink.exception.PharmacyNotFoundForUserException;
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
    public PharmacyResponseDto createPharmacy(String name, String email) {
        if (pharmacyRepository.existsByName(name)) {
            throw new PharmacyAlreadyExistsException("Pharmacy with name '" + name + "' already exists");
        }
        return mapToResponseDto(pharmacyRepository.save(mapToEntity(name, email)));
    }

    @Override
    public PharmacyResponseDto getMyPharmacy(String email) {
        PharmacyEntity pharmacy = pharmacyRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new PharmacyNotFoundForUserException("No pharmacy found for user '" + email + "'"));
        return mapToResponseDto(pharmacy);
    }

    private PharmacyEntity mapToEntity(String name, String email) {
        UserEntity owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email '" + email + "' not found"));

        return PharmacyEntity.builder()
                .name(name)
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