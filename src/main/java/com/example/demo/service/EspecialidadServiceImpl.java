package com.example.demo.service;


import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.model.especialidades;
import com.example.demo.repository.I_EspecialidadRepository;
import com.example.demo.serviceInterface.I_EspecialidadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class EspecialidadServiceImpl implements I_EspecialidadService {

    private final I_EspecialidadRepository especialidadRepository;
    @Override
    public List<especialidades> getAllEspecialidades() {
        try{
            return especialidadRepository.findAll();


        }catch (Exception e) {

            throw new RuntimeException("Failed to retrieve all Especialidades. Reason: " + e.getMessage());
        }
    }

    @Override
    public Optional<especialidades> getEspecialidadbyID(Long id) {
        Optional<especialidades> optionalEspecialidades = especialidadRepository.findById(id);
        if (optionalEspecialidades.isPresent()) {
            return especialidadRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad with ID " + id + " not found");
        }
    }

    @Override
    public especialidades guardarEspecialidad(especialidades especialidaad) {
        Optional<especialidades> optionalPaciente = especialidadRepository.findById(especialidaad.getId());

        if (optionalPaciente.isPresent()) {
            throw new PacienteAlreadyExist("ObraSocial with ID " + especialidaad.getId() + " already exists.");
        }

        try {
            return especialidadRepository.save(especialidaad);
        } catch (PacienteAlreadyExist e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public especialidades updatearEspecialidad(Long id, especialidades nuevaEspecialidad) {
        Optional<especialidades> optionalObraSocialeExisting = especialidadRepository.findById(id);
        especialidades existingEspecialidad = optionalObraSocialeExisting.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad with ID " + id + " not found"));

        existingEspecialidad.setNombre(nuevaEspecialidad.getNombre());


        return especialidadRepository.save(existingEspecialidad);
    }

    @Override
    public void eliminarEspecialidad(Long id) {
        Optional<especialidades> optionalEspecialidad = especialidadRepository.findById(id);
        if (optionalEspecialidad.isPresent()) {
            especialidadRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad with ID " + id + " not found");
        }
    }


    }

