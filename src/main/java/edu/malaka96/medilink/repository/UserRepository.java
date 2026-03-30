package edu.malaka96.medilink.repository;

import edu.malaka96.medilink.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    java.util.Optional<UserEntity> findByEmail(String email);
}
