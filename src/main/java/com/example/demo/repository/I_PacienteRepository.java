package com.example.demo.repository;

import com.example.demo.model.pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface I_PacienteRepository extends JpaRepository<pacientes, Long> {

    Optional<pacientes> findByDniPaciente(int dniPaciente);


}
