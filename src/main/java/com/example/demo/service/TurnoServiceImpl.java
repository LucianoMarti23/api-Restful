package com.example.demo.service;


import com.example.demo.model.profesionales;
import com.example.demo.model.turnos;
import com.example.demo.repository.I_CargoRepository;
import com.example.demo.repository.I_EspecialidadRepository;
import com.example.demo.repository.I_ProfesionalesRepository;
import com.example.demo.repository.I_TurnoRepository;
import com.example.demo.serviceInterface.I_TurnoService;
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
public class TurnoServiceImpl implements I_TurnoService {

    private final I_TurnoRepository turnoRepository;

    private final I_ProfesionalesRepository profesionalesRepository;

    private final I_EspecialidadRepository especialidadRepository;


    @Override
    public List<turnos> getAllTurnos() {
        try{
            return turnoRepository.findAll();


        }catch (Exception e) {

            throw new RuntimeException("Failed to retrieve all Turnos. Reason: " + e.getMessage());
        }

    }


    @Override
    public Optional<turnos> getTurnobyID(Long id) {
        Optional<turnos> optionalTurno = turnoRepository.findById(id);
        if (optionalTurno.isPresent()) {
            return turnoRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente with ID " + id + " not found");
        }


    }

    @Override
    public turnos guardarTurno(turnos turno) {
        return null;
    }

    @Override
    public turnos updatearTurno(Long id, turnos nuevoTurno) {
        return null;
    }

    @Override
    public void eliminarTurno(Long id) {

    }
}
