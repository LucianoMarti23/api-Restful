package com.example.demo.repository;

import com.example.demo.model.obra_sociales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface I_ObraSocialRepository extends JpaRepository<obra_sociales,Long> {
}
