package com.triana.salesianos.edu.satapp.user.dto;

public record LoginRequest(
        String email,
        String password
) {
}
