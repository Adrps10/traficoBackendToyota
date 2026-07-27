package com.inmotion.trafico.repository;

import com.inmotion.trafico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByTelefono(String telefono);
    boolean existsByTelefono(String telefono);
}