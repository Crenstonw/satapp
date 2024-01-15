package com.triana.salesianos.edu.satapp.ticket.service;

import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.inventariable.repo.InventariableRepository;
import com.triana.salesianos.edu.satapp.ticket.dto.AssignAdminDto;
import com.triana.salesianos.edu.satapp.ticket.dto.CreateTicketRequest;
import com.triana.salesianos.edu.satapp.ticket.dto.EditTicketRequest;
import com.triana.salesianos.edu.satapp.ticket.dto.TicketDto;
import com.triana.salesianos.edu.satapp.ticket.exception.TicketNotFoundException;
import com.triana.salesianos.edu.satapp.ticket.exception.UserNotAdminException;
import com.triana.salesianos.edu.satapp.ticket.modal.State;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.repo.TicketRepository;
import com.triana.salesianos.edu.satapp.user.exception.UserNotFoundException;
import com.triana.salesianos.edu.satapp.user.modal.User;
import com.triana.salesianos.edu.satapp.user.modal.UserRole;
import com.triana.salesianos.edu.satapp.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Optional<Ticket> optionalTicket = ticketRepository.findFirstById(UUID.fromString(id));
        if(optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            ticket.setTitle(editTicketRequest.title());
            ticket.setDescription(editTicketRequest.description());

            ticketRepository.save(ticket);
            return TicketDto.of(ticket);
        } else
            throw new TicketNotFoundException();
    }

    public void deleteTicket(String id) {
        Optional<Ticket> ticket = ticketRepository.findFirstById(UUID.fromString(id));
        if(ticket.isPresent())
            ticketRepository.delete(ticket.orElse(null));
        else
            throw new TicketNotFoundException();
    }

    public TicketDto changeState(String id) {
        Optional<Ticket> find = ticketRepository.findFirstById(UUID.fromString(id));
        if(find.isPresent()) {
            Ticket ticket = find.get();
            if(ticket.getState() == State.OPENED )
                ticket.setState(State.CLOSED);
            else
                ticket.setState(State.OPENED);
            ticketRepository.save(ticket);
            return TicketDto.of(ticket);
        } else throw new TicketNotFoundException();
    }

    public  TicketDto assignTo(String id, AssignAdminDto mail) {
        Optional<Ticket> findTicket = ticketRepository.findFirstById(UUID.fromString(id));
        Optional<User> findUser = userRepository.buscarPorEmail(mail.email());
        if (findUser.isEmpty())
            throw new UserNotFoundException();
        if (findTicket.isEmpty())
            throw new TicketNotFoundException();
        Ticket ticket = findTicket.get();
        User user = findUser.get();
        if(user.getUserRole().contains(UserRole.ADMIN)) {
            ticket.setAssignedTo(user);
            ticketRepository.save(ticket);
            return TicketDto.of(ticket);
        } else throw new UserNotAdminException();
    }

    public List<TicketDto> findAllTicketsFromInventariable(String inventariableId) {
        List<Ticket> find = inventariableRepository.findAllTicketsWhereInventariableId(UUID.fromString(inventariableId));

        if(!find.isEmpty()) {
            List<TicketDto> result = new ArrayList<>();
            for(Ticket u : find) {
                result.add(TicketDto.of(u));
            }
            return result;
        } else throw new TicketNotFoundException();
    }
}

