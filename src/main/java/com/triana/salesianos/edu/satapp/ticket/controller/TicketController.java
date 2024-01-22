package com.triana.salesianos.edu.satapp.ticket.controller;

import com.triana.salesianos.edu.satapp.inventariable.dto.CreateInventariableRequest;
import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.ticket.dto.AssignAdminDto;
import com.triana.salesianos.edu.satapp.ticket.dto.CreateTicketRequest;
import com.triana.salesianos.edu.satapp.ticket.dto.EditTicketRequest;
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
    public ResponseEntity<TicketDto> editTicket(@PathVariable String id, @RequestBody EditTicketRequest editTicketRequest) {
        return ResponseEntity.ok().body(ticketService.editTicket(id, editTicketRequest));
    }

    @DeleteMapping("/ticket/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
    public ResponseEntity<List<TicketDto>> ticketFromInventariable(@PathVariable String inventariableId) {
        return null;
    }

    @PutMapping("/ticket/{id}/estado")
    public ResponseEntity<TicketDto> ticketStatus(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.changeState(id));
    }

    @PutMapping("/ticket/{id}/asignar")
    public ResponseEntity<TicketDto> ticketAssignTechnician(@PathVariable String id, @RequestBody AssignAdminDto assignAdminDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.assignTo(id, assignAdminDto));
    }

    @GetMapping("/ticket/asignados/me")
    public ResponseEntity<Optional<Ticket>> ticketAssigntToMe() {
        return null;
    }
}
