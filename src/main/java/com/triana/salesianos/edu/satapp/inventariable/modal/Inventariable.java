package com.triana.salesianos.edu.satapp.inventariable.modal;

import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@MappedSuperclass
@SuperBuilder
@Table(name = "inventariable_entity")
public class Inventariable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "ubication")
    private String ubication;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "inventariable_id")
    private Set<Ticket> tickets = new LinkedHashSet<>();

}
