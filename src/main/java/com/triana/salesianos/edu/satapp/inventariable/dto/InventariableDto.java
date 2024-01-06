package com.triana.salesianos.edu.satapp.inventariable.dto;

import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record InventariableDto(
        UUID id,
        String name,
        String type,
        String ubication

) {
}
