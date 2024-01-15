package com.triana.salesianos.edu.satapp;

import com.triana.salesianos.edu.satapp.ticket.dto.AssignAdminDto;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import com.triana.salesianos.edu.satapp.ticket.repo.TicketRepository;
import com.triana.salesianos.edu.satapp.ticket.service.TicketService;
import org.hibernate.annotations.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.UUID;

@SpringBootTest
public class TicketServiceTest {

    @Mock
    TicketService servicio;

    @Mock
    TicketRepository repositorio;



    @Test
    @WithMockUser(username = "admin@admin.com", roles = {"ADMIN"})
    public void assignTo() {
        Mockito.when().thenReturn();
    }
}
