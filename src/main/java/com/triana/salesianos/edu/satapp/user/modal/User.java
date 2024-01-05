package com.triana.salesianos.edu.satapp.user.modal;

import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Table(name = "user_entity")
@EntityListeners(AuditingEntityListener.class)
//@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)
    protected String email;

    @Column(name = "password")
    protected String password;

    @Column(name = "username")
    protected String username;

    @Enumerated
    @Column(name = "user_role")
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<UserRole> userRole;

    /*@OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    protected Collection<Ticket> tickets = new ArrayList<>();*/

    @Builder.Default
    protected LocalDateTime lastPasswordChangedAt = LocalDateTime.now();

    @CreatedDate
    protected LocalDateTime createdAt;

    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    protected boolean accountNonLocked = false;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled = true;

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
