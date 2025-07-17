package com.example.Api_Expo_2025.Repositories;

import com.example.Api_Expo_2025.Entities.EntityCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCliente extends JpaRepository<EntityCliente, Long> {
    Optional<EntityCliente> findByEmail(String email);
}