package com.triana.salesianos.edu.satapp.ticket.repo;

import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.ticket.dto.TicketDto;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.user.dto.UserResponse;
import com.triana.salesianos.edu.satapp.user.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findFirstById(UUID id);

    @Query("""
            SELECT t FROM Ticket t
            """)
    List<TicketDto> getAllTickets();

    @Query("""
            SELECT new com.triana.salesianos.edu.satapp.ticket.dto.TicketDto(
                t.id,
                t.title,
                t.description,
                t.state
            ) FROM Ticket t
            LEFT JOIN t.inventariable i
            WHERE t.id = ?1
            """)
    Optional<TicketDto> getTicketById(UUID id);


}
