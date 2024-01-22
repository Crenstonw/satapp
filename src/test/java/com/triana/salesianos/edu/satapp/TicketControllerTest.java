package com.triana.salesianos.edu.satapp;

import com.triana.salesianos.edu.satapp.ticket.controller.TicketController;
import com.triana.salesianos.edu.satapp.ticket.dto.TicketDto;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.repo.TicketRepository;
import com.triana.salesianos.edu.satapp.ticket.service.TicketService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    @InjectMocks
    TicketService service;

    @Mock
    TicketRepository repository;

    @Test
    void getAllTickets() {
        Ticket ticket = Ticket.builder()
                .id(UUID.randomUUID())
                .title("asdad")
                .description("asdasdasd")
                .build();

        repository.save(ticket);



        List<TicketDto> result = service.getAllTickets();

        Assertions.assertEquals(1, result.size());
    }
}
