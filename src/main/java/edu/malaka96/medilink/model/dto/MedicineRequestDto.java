package edu.malaka96.medilink.model.dto;

import lombok.Data;

@Data
public class MedicineRequestDto {
    private String brandName;
    private String genericName;
    private String dosage;
    private String form;
    private String manufacturer;
    private String description;
}