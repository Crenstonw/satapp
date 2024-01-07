package com.triana.salesianos.edu.satapp.ticket.dto;

import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.ticket.modal.State;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.user.modal.User;

import java.util.UUID;

public record TicketDto(
        UUID id,
        String title,
        String description,
        State state,
        Inventariable inventariable,
        User user
) {
    public static TicketDto of(Ticket t) {
        return new TicketDto(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getState(),
                t.getInventariable(),
                t.getUser()
        );
    }
}
