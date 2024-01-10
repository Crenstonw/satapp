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

    @Query("""
            SELECT new com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto(
                i.id,
                i.name,
                i.type,
                i.ubication
            )
            FROM Ticket t
            LEFT JOIN t.inventariable i
            WHERE t.id = ?1
            """)
    Optional<Inventariable> getInventariableInf(UUID id);

    @Query("""
            SELECT new com.triana.salesianos.edu.satapp.user.dto.UserResponse(
                ob.id,
                ob.email,
                ob.username,
                ob.createdAt
            )
            FROM Ticket t
            LEFT JOIN t.openedBy ob
            WHERE t.id = ?1
            """)
    Optional<User> getOpenedByInf(UUID id);

    @Query("""
            SELECT new com.triana.salesianos.edu.satapp.user.dto.UserResponse(
                at.id,
                at.email,
                at.username,
                at.createdAt
            )
            FROM Ticket t
            LEFT JOIN t.assignedTo at
            WHERE t.id = ?1
            """)
    Optional<User> getAssignedToInf(UUID id);
}
