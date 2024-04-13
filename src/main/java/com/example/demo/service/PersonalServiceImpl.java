package com.example.demo.service;


import com.example.demo.model.cargos;

import com.example.demo.model.personales;
import com.example.demo.repository.I_CargoRepository;
import com.example.demo.repository.I_PersonalRepository;

import com.example.demo.serviceInterface.I_PersonalService;
import jakarta.persistence.EntityNotFoundException;
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
public class PersonalServiceImpl implements I_PersonalService {

    private final I_PersonalRepository personalRepository;

    private final I_CargoRepository cargoRepository;




    @Override
    public List<personales> getAllPersonales() {
        try{
            return personalRepository.findAll();


        }catch (Exception e) {

            throw new RuntimeException("Failed to retrieve all Personales. Reason: " + e.getMessage());
        }

    }




    @Override
    public Optional<personales> getPersonalbyID(Long id) {
        Optional<personales> optionalPersonal = personalRepository.findById(id);
        if (optionalPersonal.isPresent()) {
            return personalRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal with ID " + id + " not found");
        }


    }

    @Override
    public personales guardarPersonal(personales personal, Long cargoId) {

        Optional<personales> optionalCargo = personalRepository.findById(personal.getId());

        if (optionalCargo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A personal record with the same cargo ID already exists.");
        }

        try {

            cargos cargo = cargoRepository.findById(cargoId)
                    .orElseThrow(() -> new EntityNotFoundException("Cargo with ID " + cargoId + " not found"));
            personal.setTypeCargo(cargo);

            // Guardar el registro personal
            return personalRepository.save(personal);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @Override
    public personales updatearPersonales(Long id, personales nuevoPersonal) {
        Optional<personales> optionalPersonalExisting = personalRepository.findById(id);
        personales existingPersonal = optionalPersonalExisting.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal with ID " + id + " not found"));

        existingPersonal.setApellido(nuevoPersonal.getApellido());
        existingPersonal.setNombre(nuevoPersonal.getNombre());
        existingPersonal.setFecha_fin(nuevoPersonal.getFecha_fin());
        existingPersonal.setFecha_inicio(nuevoPersonal.getFecha_inicio());
        existingPersonal.setTypeCargo(nuevoPersonal.getTypeCargo());
        return personalRepository.save(existingPersonal);
    }

    @Override
    public void eliminarPersonal(Long id) {
        Optional<personales> optionalPersonal = personalRepository.findById(id);
        if (optionalPersonal.isPresent()) {
            personalRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente with DNI " + id + " not found");
        }

    }






}
