package com.triana.salesianos.edu.satapp;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.inventariable.repo.InventariableRepository;
import com.triana.salesianos.edu.satapp.user.modal.User;
import com.triana.salesianos.edu.satapp.user.modal.UserRole;
import com.triana.salesianos.edu.satapp.user.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final InventariableRepository inventariableRepository;

    @PostConstruct
    public  void initData(){
        User admin = User.builder()
                .id(UUID.randomUUID())
                .email("admin@admin.com")
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .userRole(EnumSet.of(UserRole.ADMIN))
                .enabled(true)
                .build();
        userRepository.save(admin);

        Inventariable mesa = Inventariable.builder()
                .id(UUID.randomUUID())
                .name("mesa")
                .type("mueble")
                .ubication("Sevilla")
                .build();
        inventariableRepository.save(mesa);
    }
}
