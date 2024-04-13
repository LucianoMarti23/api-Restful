package com.example.demo.serviceInterface;

import com.example.demo.model.especialidades;
import com.example.demo.model.obra_sociales;

import java.util.List;
import java.util.Optional;

public interface I_EspecialidadService {

    List<especialidades> getAllEspecialidades();

    Optional<especialidades> getEspecialidadbyID(Long id);


    especialidades guardarEspecialidad(especialidades especialidaad);


    especialidades updatearEspecialidad(Long id , especialidades nuevaEspecialidad);

    void eliminarEspecialidad(Long id);


}
