package com.example.demo.serviceInterface;

import com.example.demo.dto.PacienteDTO;


import java.util.List;


public interface I_PacienteService {

    List<PacienteDTO> getAllPacientes();

    PacienteDTO getPacientebyDNI(int id);


    void guardarPaciente(PacienteDTO paciente);


    String updatearPaciente(int dni , PacienteDTO nuevoPaciente);

    void eliminarPaciente(int dni);



}
