package edu.malaka96.medilink.model.dto;

import lombok.Data;

@Data
public class AdminUserRequestDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String roleName;
}
