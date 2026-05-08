package edu.malaka96.medilink.config;

import edu.malaka96.medilink.exception.RoleAlreadyExistsException;
import edu.malaka96.medilink.model.dto.RoleRequestDto;
import edu.malaka96.medilink.model.entity.RoleEntity;
import edu.malaka96.medilink.model.entity.UserEntity;
import edu.malaka96.medilink.repository.RoleRepository;
import edu.malaka96.medilink.repository.UserRepository;
import edu.malaka96.medilink.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        List.of("ADMIN", "PHARMACY").forEach(name -> {
            try {
                RoleRequestDto dto = new RoleRequestDto();
                dto.setName(name);
                roleService.createRole(dto);
            } catch (RoleAlreadyExistsException ignored) {}
        });

        if (!userRepository.existsByEmail("m@gmail.com")) {
            RoleEntity adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            userRepository.save(UserEntity.builder()
                    .name("Malaka Madhubhashana")
                    .email("m@gmail.com")
                    .password(passwordEncoder.encode("123123"))
                    .phone("0704132218")
                    .roleEntity(adminRole)
                    .status("ACTIVE")
                    .build());
        }
    }
}
