package com.project.sibaia.config;

import com.project.sibaia.repository.AdministradorRepository;
import com.project.sibaia.entity.Administrador;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(
            AdministradorRepository adminRepo,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (adminRepo.count() == 0) {
                Administrador admin = new Administrador();
                admin.setNombre("Administrador Default");
                admin.setUsuario("admin");

                admin.setPassword(passwordEncoder.encode("admin123"));

                adminRepo.save(admin);
            }
        };
    }
}
