package edu.malaka96.medilink.model.dto;

import lombok.Data;

@Data
public class MedicineAliasRequestDto {
    private Long medicineId;
    private String aliasName;
}