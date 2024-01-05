package com.triana.salesianos.edu.satapp.ticket.controller;

import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket/new")
    public ResponseEntity<Ticket> newTicket() {
        return null;
    }

    @PutMapping("/ticket/edit/{id}")
    public ResponseEntity<Ticket> editTicket(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/ticket/delete/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable String id) {
        return null;
    }

    //Solo administradores

    @GetMapping("/ticket/all")
    public ResponseEntity<Optional<Ticket>> allTickets() {
        return null;
    }

    @GetMapping("/ticket/inventariable/{inventariableId}")
    public ResponseEntity<Optional<Ticket>> ticketFromInventariable(@PathVariable String inventariableId) {
        return null;
    }

    @PutMapping("/ticket/{id}/estado")
    public ResponseEntity<Ticket> ticketStatus(@PathVariable String id) {
        return null;
    }

    @PutMapping("/ticket/{id}/asignar")
    public ResponseEntity<Ticket> ticketAssignTechnician(@PathVariable String id) {
        return null;
    }

    @GetMapping("/ticket/asignados/me")
    public ResponseEntity<Optional<Ticket>> ticketAssigntToMe() {
        return null;
    }
}
