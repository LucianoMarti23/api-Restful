package com.example.demo.serviceInterface;


import com.example.demo.model.turnos;

import java.util.List;
import java.util.Optional;

public interface I_TurnoService {


    List<turnos> getAllTurnos();

    Optional<turnos> getTurnobyID(Long id);


    turnos guardarTurno(turnos turno);


    turnos updatearTurno(Long id , turnos nuevoTurno);

    void eliminarTurno(Long id);

}
