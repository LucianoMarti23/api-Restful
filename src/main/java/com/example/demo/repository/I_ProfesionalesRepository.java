package com.example.demo.repository;

import com.example.demo.model.profesionales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface I_ProfesionalesRepository extends JpaRepository<profesionales, Long> {

    Optional<profesionales> findByDniProfesional(int dniProfesional);

}
