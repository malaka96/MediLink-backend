package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.AdminUserRequestDto;
import edu.malaka96.medilink.model.dto.UserRequestDto;
import edu.malaka96.medilink.model.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto createUserByAdmin(AdminUserRequestDto adminUserRequestDto);
    UserResponseDto getUserByEmail(String email);
}
