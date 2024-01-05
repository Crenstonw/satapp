package com.triana.salesianos.edu.satapp.ticket.repo;

import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<UUID, Ticket> {
}
