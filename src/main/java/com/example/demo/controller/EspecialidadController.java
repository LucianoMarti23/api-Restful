package com.example.demo.controller;


import com.example.demo.exceptions.CustomHandler;
import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.model.especialidades;

import com.example.demo.serviceInterface.I_EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Especialidades")
public class EspecialidadController {

   private final I_EspecialidadService service;


    @GetMapping("/listEspecialidades")
    public ResponseEntity<?> listEspecialidades() {
        try {
            return new ResponseEntity<>(service.getAllEspecialidades() , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Obra_Sociales not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEspecialidad(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(service.getEspecialidadbyID(id) , HttpStatus.OK);

        } catch (ResponseStatusException e) {

            return new ResponseEntity<>(e.getReason() , e.getStatusCode());
        }
    }


    @PostMapping("/newEspecialidad")
    public ResponseEntity<?> add(@RequestBody especialidades especialidad) {
        try {
            service.guardarEspecialidad(especialidad);
            return new ResponseEntity<>(especialidad, HttpStatus.CREATED);
        } catch (PacienteAlreadyExist
                e) {
            // Handle the specific exception thrown by the service
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Handle other runtime exceptions, you might want to log them or return a generic error response
            return new ResponseEntity<>("Error at save Especialidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/updateEspecialidad")
    public ResponseEntity<?> updateEspecialidad(@RequestBody especialidades especialidad) {
        try {
            especialidades updatedObraSocial = service.updatearEspecialidad(especialidad.getId(), especialidad);
            return new ResponseEntity<>(updatedObraSocial, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (CustomHandler e) {
            return new ResponseEntity<>("Error al actualizar Especialidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteEspecialidad/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            service.eliminarEspecialidad(id);
            return new ResponseEntity<>("Especialidad eliminada correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }



}
