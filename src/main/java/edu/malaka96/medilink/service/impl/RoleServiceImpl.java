package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.RoleAlreadyExistsException;
import edu.malaka96.medilink.model.dto.RoleRequestDto;
import edu.malaka96.medilink.model.dto.RoleResponseDto;
import edu.malaka96.medilink.model.entity.RoleEntity;
import edu.malaka96.medilink.repository.RoleRepository;
import edu.malaka96.medilink.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        if (roleRepository.existsByName(roleRequestDto.getName())) {
            throw new RoleAlreadyExistsException("Role with name '" + roleRequestDto.getName() + "' already exists");
        }
        
        RoleEntity roleEntity = mapToEntity(roleRequestDto);
        RoleEntity savedRole = roleRepository.save(roleEntity);
        return mapToResponseDto(savedRole);
    }

    private RoleEntity mapToEntity(RoleRequestDto roleRequestDto) {
        return RoleEntity.builder()
                .name(roleRequestDto.getName())
                .build();
    }

    private RoleResponseDto mapToResponseDto(RoleEntity roleEntity) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(roleEntity.getId());
        responseDto.setName(roleEntity.getName());
        return responseDto;
    }
}