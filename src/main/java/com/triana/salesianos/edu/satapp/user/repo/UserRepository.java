package com.triana.salesianos.edu.satapp.user.repo;

import com.triana.salesianos.edu.satapp.user.dto.UserResponse;
import com.triana.salesianos.edu.satapp.user.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByEmail(String email);

    @Query("""
            
            """)
    Optional<UserResponse> findByNoValidated();
}
