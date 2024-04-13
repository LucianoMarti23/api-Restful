package com.example.demo.repository;


import com.example.demo.model.cargos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_CargoRepository extends JpaRepository<cargos, Long> {
}
