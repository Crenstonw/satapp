package com.triana.salesianos.edu.satapp.ticket.dto;


public record CreateTicketRequest(
        String title,
        String description,
        String inventariableId

) {
}
