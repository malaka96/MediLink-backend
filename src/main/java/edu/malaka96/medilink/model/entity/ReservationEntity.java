package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    private Integer quantity;

    @Column(unique = true)
    private String reservationCode;

    private String status; // ACTIVE, EXPIRED, COMPLETED

    @CreationTimestamp
    private Timestamp createdAt;
    private LocalDateTime expiresAt;
}
