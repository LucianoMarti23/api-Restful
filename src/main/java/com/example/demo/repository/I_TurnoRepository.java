package com.example.demo.repository;



import com.example.demo.model.turnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_TurnoRepository extends JpaRepository<turnos,Long> {
}
