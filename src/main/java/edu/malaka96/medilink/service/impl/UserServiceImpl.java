package edu.malaka96.medilink.service.impl;

import edu.malaka96.medilink.exception.RoleNotFoundException;
import edu.malaka96.medilink.exception.UserAlreadyExistsException;
import edu.malaka96.medilink.exception.UserNotFoundException;
import edu.malaka96.medilink.model.dto.AdminUserRequestDto;
import edu.malaka96.medilink.model.dto.UserRequestDto;
import edu.malaka96.medilink.model.dto.UserResponseDto;
import edu.malaka96.medilink.model.entity.RoleEntity;
import edu.malaka96.medilink.model.entity.UserEntity;
import edu.malaka96.medilink.repository.RoleRepository;
import edu.malaka96.medilink.repository.UserRepository;
import edu.malaka96.medilink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email '" + userRequestDto.getEmail() + "' already exists");
        }
        RoleEntity role = roleRepository.findByName("PHARMACY")
                .orElseThrow(() -> new RoleNotFoundException("Role PHARMACY not found"));
        return mapToResponseDto(userRepository.save(UserEntity.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .phone(userRequestDto.getPhone())
                .roleEntity(role)
                .build()));
    }

    @Override
    @Transactional
    public UserResponseDto createUserByAdmin(AdminUserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("User with email '" + dto.getEmail() + "' already exists");
        }
        RoleEntity role = roleRepository.findByName(dto.getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Role '" + dto.getRoleName() + "' not found"));
        return mapToResponseDto(userRepository.save(UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .roleEntity(role)
                .build()));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::mapToResponseDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    private UserResponseDto mapToResponseDto(UserEntity userEntity) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(userEntity.getId());
        responseDto.setName(userEntity.getName());
        responseDto.setEmail(userEntity.getEmail());
        responseDto.setPhone(userEntity.getPhone());
        responseDto.setRole(userEntity.getRoleEntity() != null ? userEntity.getRoleEntity().getName() : null);
        responseDto.setStatus(userEntity.getStatus());
        responseDto.setCreatedAt(userEntity.getCreatedAt());
        return responseDto;
    }
}