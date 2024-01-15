package com.triana.salesianos.edu.satapp.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.triana.salesianos.edu.satapp.user.modal.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    protected String id;
    protected String email, username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUserUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
