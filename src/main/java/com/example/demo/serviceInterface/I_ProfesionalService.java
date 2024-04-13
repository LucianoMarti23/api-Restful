package com.example.demo.serviceInterface;

import com.example.demo.dto.ProfesionalDTO;
import java.util.List;
import java.util.Optional;

public interface I_ProfesionalService {

    List<?> getAllProfesional();

    Optional<ProfesionalDTO> getProfesionalbyDNI(int dni);


    void guardarProfesional(ProfesionalDTO profesional);


    void updatearProfesional(int dni , ProfesionalDTO nuevoProfesional);

    void eliminarProfesional(int dni);





}
