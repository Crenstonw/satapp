package com.triana.salesianos.edu.satapp.ticket.service;

import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.inventariable.repo.InventariableRepository;
import com.triana.salesianos.edu.satapp.ticket.dto.CreateTicketRequest;
import com.triana.salesianos.edu.satapp.ticket.dto.TicketDto;
import com.triana.salesianos.edu.satapp.ticket.modal.State;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.repo.TicketRepository;
import com.triana.salesianos.edu.satapp.user.modal.User;
import com.triana.salesianos.edu.satapp.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
        System.out.println(userDetails.getUsername());
        Optional<User> user = userRepository.findFirstByEmail(userDetails.getUsername());
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
}

