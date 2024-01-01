package com.triana.salesianos.edu.satapp.user.modal;

import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Table(name = "user_entity")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)
    protected String email;

    @Column(name = "password")
    protected String password;

    @Column(name = "name")
    protected String name;

    @Enumerated
    @Column(name = "user_role")
    private UserRole userRole;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Collection<Ticket> tickets = new ArrayList<>();

}
