package com.example.demo.repository;

import com.example.demo.model.especialidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_EspecialidadRepository extends JpaRepository<especialidades,Long> {
}
