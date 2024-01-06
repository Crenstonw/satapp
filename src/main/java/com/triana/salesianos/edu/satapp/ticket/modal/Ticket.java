package com.triana.salesianos.edu.satapp.ticket.modal;

import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.user.modal.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
//@MappedSuperclass
@Table(name = "ticket_entity")
public class Ticket {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated
    @Column(name = "state")
    private State state;

    @ManyToOne
    @JoinColumn(name = "inventariable_id")
    private Inventariable inventariable;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
