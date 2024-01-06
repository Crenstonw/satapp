package com.triana.salesianos.edu.satapp.user.dto;

import com.triana.salesianos.edu.satapp.user.modal.UserRole;

import java.util.Set;
import java.util.UUID;

public record UserNoValidatedRequest(
        UUID id,
        String username,
        String email,
        String password

) {
}
