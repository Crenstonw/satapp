package com.triana.salesianos.edu.satapp.user.dto;

public record CreateUserRequest(
        String email,
        String password,
        String verifyPassword,
        String username
) {
}
