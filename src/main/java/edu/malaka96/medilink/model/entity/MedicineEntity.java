package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandName;
    private String genericName;
    private String dosage;
    private String form;
    private String manufacturer;

    @Column(columnDefinition = "TEXT")
    private String description;
}
