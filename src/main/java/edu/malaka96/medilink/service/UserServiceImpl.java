package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.UserRequestDto;
import edu.malaka96.medilink.model.dto.UserResponseDto;
import edu.malaka96.medilink.model.entity.RoleEntity;
import edu.malaka96.medilink.model.entity.UserEntity;
import edu.malaka96.medilink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return mapToResponseDto(userRepository.save(mapToEntity(userRequestDto)));
    }

    private UserEntity mapToEntity(UserRequestDto userRequestDto) {
        return UserEntity.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .phone(userRequestDto.getPhone())
                .roleEntity(RoleEntity.builder().id(userRequestDto.getRoleId()).build())
                .build();
    }

    private UserResponseDto mapToResponseDto(UserEntity userEntity) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(userEntity.getId());
        responseDto.setName(userEntity.getName());
        responseDto.setEmail(userEntity.getEmail());
        responseDto.setPhone(userEntity.getPhone());
        responseDto.setStatus(userEntity.getStatus());
        responseDto.setCreatedAt(userEntity.getCreatedAt());
        return responseDto;
    }
}