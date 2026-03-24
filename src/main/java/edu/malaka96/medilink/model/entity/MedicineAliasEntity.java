package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicine_aliases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineAliasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicine;

    private String aliasName;
}
