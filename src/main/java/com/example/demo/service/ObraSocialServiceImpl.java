package com.example.demo.service;

import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.model.obra_sociales;
import com.example.demo.model.pacientes;
import com.example.demo.repository.I_ObraSocialRepository;
import com.example.demo.serviceInterface.I_ObraSocialService;
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
public class ObraSocialServiceImpl implements I_ObraSocialService {


    private final I_ObraSocialRepository obraSocialRepository;

    @Override
    public List<obra_sociales> getAllObraSocial() {
        try{
            return obraSocialRepository.findAll();


        }catch (Exception e) {

            throw new RuntimeException("Failed to retrieve all Obra Sociales. Reason: " + e.getMessage());
        }


     }

    @Override
    public Optional<obra_sociales> getObraSocialbyID(Long id) {
        Optional<obra_sociales> optionalObraSociales = obraSocialRepository.findById(id);
        if (optionalObraSociales.isPresent()) {
            return obraSocialRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente with ID " + id + " not found");
        }


    }

    @Override
    public obra_sociales guardarObraSocial(obra_sociales obraSocial) {
        Optional<obra_sociales> optionalPaciente = obraSocialRepository.findById(obraSocial.getId());

        if (optionalPaciente.isPresent()) {
            throw new PacienteAlreadyExist("ObraSocial with ID " + obraSocial.getId() + " already exists.");
        }

        try {
            return obraSocialRepository.save(obraSocial);
        } catch (PacienteAlreadyExist e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public obra_sociales updatearObraSocial(Long id, obra_sociales nuevaObraSocial) {
        Optional<obra_sociales> optionalObraSocialeExisting = obraSocialRepository.findById(id);
        obra_sociales existingObraSocial = optionalObraSocialeExisting.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "ObraSocial with ID " + id + " not found"));

        existingObraSocial.setDescripcion(nuevaObraSocial.getDescripcion());


        return obraSocialRepository.save(existingObraSocial);
    }

    @Override
    public void eliminarObraSocial(Long id) {
        Optional<obra_sociales> optionalObraSocial = obraSocialRepository.findById(id);
        if (optionalObraSocial.isPresent()) {
            obraSocialRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ObraSocial with ID " + id + " not found");
        }
    }
}
