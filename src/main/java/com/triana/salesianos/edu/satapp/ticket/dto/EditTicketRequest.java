package com.triana.salesianos.edu.satapp.ticket.dto;

import com.triana.salesianos.edu.satapp.ticket.modal.State;

public record EditTicketRequest(
        String title,
        String description
) {
}
