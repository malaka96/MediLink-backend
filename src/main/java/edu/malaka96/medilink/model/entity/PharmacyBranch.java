package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pharmacy_branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private PharmacyEntity pharmacyEntity;

    private String name;
    private String address;

    private Double latitude;
    private Double longitude;

    private String contactNumber;
}
