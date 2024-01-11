package com.triana.salesianos.edu.satapp.ticket.service;

import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.inventariable.repo.InventariableRepository;
import com.triana.salesianos.edu.satapp.ticket.dto.CreateTicketRequest;
import com.triana.salesianos.edu.satapp.ticket.dto.EditTicketRequest;
import com.triana.salesianos.edu.satapp.ticket.dto.TicketDto;
import com.triana.salesianos.edu.satapp.ticket.exception.TicketNotFoundException;
import com.triana.salesianos.edu.satapp.ticket.modal.State;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.repo.TicketRepository;
import com.triana.salesianos.edu.satapp.user.dto.UserResponse;
import com.triana.salesianos.edu.satapp.user.modal.User;
import com.triana.salesianos.edu.satapp.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final InventariableRepository inventariableRepository;
    private final UserRepository userRepository;
    public TicketDto createNewTicket(CreateTicketRequest createTicketRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.buscarPorEmail(userDetails.getUsername());
        Optional<Inventariable> inventariable = inventariableRepository.findById(UUID.fromString(createTicketRequest.inventariableId()));
        Ticket newTicket = Ticket.builder()
                .id(UUID.randomUUID())
                .title(createTicketRequest.title())
                .description(createTicketRequest.description())
                .state(State.OPENED)
                .inventariable(inventariable.orElse(null))
                .openedBy(user.orElse(null))
                .build();
        Ticket result = ticketRepository.save(newTicket);
        return TicketDto.of(result);
    }

    public List<TicketDto> getAllTickets() {
        return ticketRepository.getAllTickets();
    }

    public TicketDto editTicket(String id, EditTicketRequest editTicketRequest) {
        UUID uuidId = UUID.fromString(id);
        Optional<TicketDto> ticket = ticketRepository.getTicketById(uuidId);
        Optional<Inventariable> inventariable = ticketRepository.getInventariableInf(uuidId);
        Optional<User> openedBy = ticketRepository.getOpenedByInf(uuidId);
        Optional<User> assignedTo = ticketRepository.getAssignedToInf(uuidId);
        Ticket result = Ticket.builder()
                .id(ticket.get().id())
                .title(editTicketRequest.title())
                .description(editTicketRequest.description())
                .state(ticket.get().state())
                .inventariable(inventariable.orElse(null))
                .openedBy(openedBy.orElse(null))
                .assignedTo(assignedTo.orElse(null))
                .build();
        ticketRepository.save(result);
        return TicketDto.of(result);

    }

    public void deleteTicket(String id) {
        Optional<Ticket> ticket = ticketRepository.findFirstById(UUID.fromString(id));
        if(ticket.isPresent())
            ticketRepository.delete(ticket.orElse(null));
        else
            throw new TicketNotFoundException();
    }
}

