package edu.malaka96.medilink.service;

import edu.malaka96.medilink.model.dto.RoleRequestDto;
import edu.malaka96.medilink.model.dto.RoleResponseDto;

public interface RoleService {
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);
}