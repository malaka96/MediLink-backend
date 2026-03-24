package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "inventory",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"pharmacy_branch_id", "medicine_id", "batch_number"}
        ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pharmacy_branch_id")
    private PharmacyBranch pharmacyBranch;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicine;

    private Integer quantity;
    private Integer reservedQuantity = 0;

    private String batchNumber;
    private LocalDate expiryDate;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
