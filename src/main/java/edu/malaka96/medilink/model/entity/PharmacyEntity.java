package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "pharmacies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @CreationTimestamp
    private Timestamp createdAt;
}
