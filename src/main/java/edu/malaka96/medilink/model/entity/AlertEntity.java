package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    private String alertType; // LOW_STOCK, OUT_OF_STOCK, EXPIRY

    @Column(columnDefinition = "TEXT")
    private String message;

    private Boolean isRead = false;

    @CreationTimestamp
    private Timestamp createdAt;
}
