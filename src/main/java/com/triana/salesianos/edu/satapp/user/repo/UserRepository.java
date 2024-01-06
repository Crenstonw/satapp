package com.triana.salesianos.edu.satapp.user.repo;

import com.triana.salesianos.edu.satapp.user.dto.CreateUserRequest;
import com.triana.salesianos.edu.satapp.user.dto.UserNoValidatedRequest;
import com.triana.salesianos.edu.satapp.user.dto.UserResponse;
import com.triana.salesianos.edu.satapp.user.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
            SELECT u FROM User u WHERE u.email = ?1
            """)
    Optional<User> findFirstByEmail(String email);

    Optional<User> findFirstByUsername(String username);

    @Query("""
            SELECT new com.triana.salesianos.edu.satapp.user.dto.UserNoValidatedRequest(
            u.id,
            u.username,
            u.email, 
            u.password
            )
            FROM User u 
            WHERE u.enabled = FALSE
            """)
    Optional<UserNoValidatedRequest> findAllNonValidated();
}
