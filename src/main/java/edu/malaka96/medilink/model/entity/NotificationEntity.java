package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String type;

    private Boolean isRead = false;

    @CreationTimestamp
    private Timestamp createdAt;
}
