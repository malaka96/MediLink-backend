package edu.malaka96.medilink.controller;

import edu.malaka96.medilink.exception.RoleAlreadyExistsException;
import edu.malaka96.medilink.model.dto.RoleRequestDto;
import edu.malaka96.medilink.model.dto.RoleResponseDto;
import edu.malaka96.medilink.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto) {
        RoleResponseDto createdRole = roleService.createRole(roleRequestDto);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity<String> handleRoleAlreadyExists(RoleAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}