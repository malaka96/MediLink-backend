package edu.malaka96.medilink.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}