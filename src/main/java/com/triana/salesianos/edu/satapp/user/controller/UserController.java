package com.triana.salesianos.edu.satapp.user.controller;

import com.triana.salesianos.edu.satapp.security.jwt.JwtProvider;
import com.triana.salesianos.edu.satapp.user.dto.*;
import com.triana.salesianos.edu.satapp.user.exception.UserNotFoundException;
import com.triana.salesianos.edu.satapp.user.modal.User;
import com.triana.salesianos.edu.satapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createUSerWithUserRole(
            @RequestBody CreateUserRequest createUserRequest
            ) {
        User user = userService.createUserWithUserRole(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromUser(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            loginRequest.password(),
                            loginRequest.email()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(user, token));
    }

    @PutMapping("/users/{userId}/validate")
    public ResponseEntity<String> validateUser(@PathVariable String userId) {
        UUID id = UUID.fromString(userId);
        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {
            User validUser = user.get();
            userService.validate(id, validUser);
            return ResponseEntity.status(HttpStatus.OK).body("Validado con éxito");
        } else {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/users/no-validated")
    public ResponseEntity<Optional<UserNoValidatedRequest>> noValidatedUserList() {
        return null;
    }
}
