package com.example.demo.controller;

import com.example.demo.dto.ProfesionalDTO;
import com.example.demo.exceptions.PacienteAlreadyExist;


import com.example.demo.serviceInterface.I_ProfesionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Profesionales")
public class ProfesionalController {

    private final I_ProfesionalService service;


    @GetMapping("/listProfesionales")
    public ResponseEntity<?> listProfesional() {
        try {
            return new ResponseEntity<>(service.getAllProfesional(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Profesionales not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{dni}")
    public ResponseEntity<?> getProfesional(@PathVariable int dni) {

        try {
            return new ResponseEntity<>(service.getProfesionalbyDNI(dni) , HttpStatus.OK);

        } catch (ResponseStatusException e) {

            return new ResponseEntity<>(e.getReason() , e.getStatusCode());
        }
    }


    @PostMapping("/newProfesional")
    public ResponseEntity<?> add(@RequestBody ProfesionalDTO nuevoProfesional) {
        try {
            service.guardarProfesional(nuevoProfesional);
            return new ResponseEntity<>(nuevoProfesional, HttpStatus.CREATED);
        } catch (PacienteAlreadyExist
                e) {
            // Handle the specific exception thrown by the service
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            // Handle other runtime exceptions, you might want to log them or return a generic error response
            return new ResponseEntity<>("Error at save Profesional", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/updateProfesional/{dni}")
    public ResponseEntity<?> updateProfesional(@RequestBody ProfesionalDTO profesional , @PathVariable int dni) {
        try {
            service.updatearProfesional(dni, profesional);
            return new ResponseEntity<>("Paciente Actualizado Correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al actualizar Profesional", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<String> deleteProfesional(@PathVariable int dni) {
        try {
            service.eliminarProfesional(dni);
            return new ResponseEntity<>("Profesional eliminado correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }



}


