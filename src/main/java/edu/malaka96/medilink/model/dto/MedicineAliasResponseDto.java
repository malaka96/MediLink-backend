package edu.malaka96.medilink.model.dto;

import lombok.Data;

@Data
public class MedicineAliasResponseDto {
    private Long id;
    private Long medicineId;
    private String aliasName;
}