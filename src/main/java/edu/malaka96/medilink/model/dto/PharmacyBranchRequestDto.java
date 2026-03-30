package edu.malaka96.medilink.model.dto;

import lombok.Data;

@Data
public class PharmacyBranchRequestDto {
    private Long pharmacyId;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String contactNumber;
}