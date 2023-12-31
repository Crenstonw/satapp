package com.triana.salesianos.edu.satapp.ticket.controller;

import com.triana.salesianos.edu.satapp.inventariable.dto.CreateInventariableRequest;
import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.ticket.dto.CreateTicketRequest;
import com.triana.salesianos.edu.satapp.ticket.dto.TicketDto;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.service.TicketService;
import com.triana.salesianos.edu.satapp.user.exception.EmptyListException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket/new")
    public ResponseEntity<TicketDto> newTicket(@RequestBody CreateTicketRequest createTicketRequest) {
        TicketDto ticket = ticketService.createNewTicket(createTicketRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
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
    public ResponseEntity<List<TicketDto>> allTickets() {
        List<TicketDto> result = ticketService.getAllTickets();

        if(result.isEmpty())
            throw new EmptyListException();
        else
            return ResponseEntity.ok().body(result);

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
