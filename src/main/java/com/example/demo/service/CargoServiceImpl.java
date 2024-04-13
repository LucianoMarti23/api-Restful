package com.example.demo.service;


import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.model.cargos;
import com.example.demo.repository.I_CargoRepository;
import com.example.demo.serviceInterface.I_CargoService;
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
public class CargoServiceImpl implements I_CargoService {

    private final I_CargoRepository cargoRepository;

    @Override
    public List<cargos> getAllCargos() {
        try{
            return cargoRepository.findAll();


        }catch (Exception e) {

            throw new RuntimeException("Failed to retrieve all Cargos. Reason: " + e.getMessage());
        }
    }

    @Override
    public Optional<cargos> getCargobyID(Long id) {
        Optional<cargos> optionalCargos = cargoRepository.findById(id);
        if (optionalCargos.isPresent()) {
            return cargoRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cargo with ID " + id + " not found");
        }
    }

    @Override
    public cargos guardarCargo(cargos cargo) {
        Optional<cargos> optionalCargo = cargoRepository.findById(cargo.getId());

        if (optionalCargo.isPresent()) {
            throw new PacienteAlreadyExist("Cargo with ID " + cargo.getId() + " already exists.");
        }

        try {
            return cargoRepository.save(cargo);
        } catch (PacienteAlreadyExist e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Override
    public cargos updatearCargo(Long id, cargos nuevaCargo) {
        Optional<cargos> optionalCargo = cargoRepository.findById(id);
        cargos existingCargo = optionalCargo.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad with ID " + id + " not found"));

        existingCargo.setNombre(nuevaCargo.getNombre());


        return cargoRepository.save(existingCargo);


    }

    @Override
    public void eliminarCargo(Long id) {
        Optional<cargos> optionalCargos = cargoRepository.findById(id);
        if (optionalCargos.isPresent()) {
            cargoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cargo with ID " + id + " not found");
        }
    }



    }

