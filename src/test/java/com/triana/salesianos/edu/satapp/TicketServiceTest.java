package com.triana.salesianos.edu.satapp;

import com.triana.salesianos.edu.satapp.ticket.dto.TicketDto;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.repo.TicketRepository;
import com.triana.salesianos.edu.satapp.ticket.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TicketServiceTest {

    @Mock
    TicketService servicio;

    @Mock
    TicketRepository repositorio;

    @Autowired
    TestEntityManager entityManager;

    //@Test
    //@WithMockUser(username = "admin@admin.com", roles = {"ADMIN"})
    //public void assignTo() {
        //Mockito.when().thenReturn();
    //}

    @Test
    void deleteTicketById() {
        Ticket t1 = Ticket.builder()
                .id(UUID.randomUUID())
                .title("hola")
                .description("soy el nono")
                .build();
        Ticket t2 = Ticket.builder()
                .id(UUID.randomUUID())
                .title("adios")
                .description("soy tu padre")
                .build();

        entityManager.merge(t1);
        entityManager.merge(t2);

        servicio.deleteTicket(t1.getId().toString());

        List<TicketDto> resultado = repositorio.getAllTickets();

        assertEquals(1, resultado.size());
    }
}
