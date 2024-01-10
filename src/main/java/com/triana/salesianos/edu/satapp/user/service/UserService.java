package com.triana.salesianos.edu.satapp.user.service;

import com.triana.salesianos.edu.satapp.user.dto.CreateUserRequest;
import com.triana.salesianos.edu.satapp.user.dto.UserNoValidatedRequest;
import com.triana.salesianos.edu.satapp.user.modal.User;
import com.triana.salesianos.edu.satapp.user.modal.UserRole;
import com.triana.salesianos.edu.satapp.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User createUser(CreateUserRequest createUserRequest, Set<UserRole> roles) {
        User user = User.builder()
                .email(createUserRequest.email())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .username(createUserRequest.username())
                .userRole(roles)
                .build();
        return userRepository.save(user);
    }

    public User createUserWithUserRole(CreateUserRequest createUserRequest) {
        return createUser(createUserRequest, Set.of(UserRole.USER));
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.buscarPorEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public void validate(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    public List<UserNoValidatedRequest> findNonValidated() {
        List<UserNoValidatedRequest> busqueda = userRepository.findAllNonValidated();
        return busqueda;
    }
}
