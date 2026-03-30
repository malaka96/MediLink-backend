package edu.malaka96.medilink.model.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class PharmacyResponseDto {
    private Long id;
    private String name;
    private String ownerEmail;
    private Timestamp createdAt;
}