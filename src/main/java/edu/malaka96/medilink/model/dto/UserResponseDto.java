package edu.malaka96.medilink.model.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private Timestamp createdAt;
}