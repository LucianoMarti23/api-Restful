package com.example.demo.repository;

import com.example.demo.model.personales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface I_PersonalRepository extends JpaRepository<personales, Long> {
    Optional<personales> findByTypeCargoId(Long cargoId);
}
