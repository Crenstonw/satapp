package com.triana.salesianos.edu.satapp.ticket.dto;

import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.ticket.modal.State;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.user.dto.UserResponse;
import com.triana.salesianos.edu.satapp.user.modal.User;

import java.util.UUID;

public record TicketDto(
        UUID id,
        String title,
        String description,
        State state,
        InventariableDto inventariable,
        UserResponse openedBy,
        UserResponse assignedTo

) {
    public static TicketDto of(Ticket t) {
        InventariableDto inventariable = InventariableDto.of(t.getInventariable());
        UserResponse openedBy = UserResponse.fromUser(t.getOpenedBy());
        UserResponse assignedTo = t.getAssignedTo() != null ? UserResponse.fromUser(t.getAssignedTo()) : null;
        return new TicketDto(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getState(),
                inventariable,
                openedBy,
                assignedTo
        );
    }
}
