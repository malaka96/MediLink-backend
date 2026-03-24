package edu.malaka96.medilink.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "search_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String keyword;

    @CreationTimestamp
    private Timestamp searchedAt;
}
