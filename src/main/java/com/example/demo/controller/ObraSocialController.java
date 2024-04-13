package com.example.demo.controller;


import com.example.demo.exceptions.CustomHandler;
import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.model.obra_sociales;

import com.example.demo.serviceInterface.I_ObraSocialService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Obra-Sociales")
public class ObraSocialController {

    private final I_ObraSocialService service;


    @GetMapping("/list-Obra-Sociales")
    public ResponseEntity<?> listObraSociales() {
        try {
            return new ResponseEntity<>(service.getAllObraSocial() , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Obra_Sociales not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getObraSocial(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(service.getObraSocialbyID(id) , HttpStatus.OK);

        } catch (ResponseStatusException e) {

            return new ResponseEntity<>(e.getReason() , e.getStatusCode());
        }
    }


    @PostMapping("/newObraSocial")
    public ResponseEntity<?> add(@RequestBody obra_sociales obraSocial) {
        try {
            service.guardarObraSocial(obraSocial);
            return new ResponseEntity<>(obraSocial, HttpStatus.CREATED);
        } catch (PacienteAlreadyExist
                e) {
            // Handle the specific exception thrown by the service
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Handle other runtime exceptions, you might want to log them or return a generic error response
            return new ResponseEntity<>("Error at save ObraSocial", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateObraSocial")
    public ResponseEntity<?> updateProduct(@RequestBody obra_sociales obraSocial) {
        try {
            obra_sociales updatedObraSocial = service.updatearObraSocial(obraSocial.getId(), obraSocial);
            return new ResponseEntity<>(updatedObraSocial, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (CustomHandler e) {
            return new ResponseEntity<>("Error al actualizar ObraSocial", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteObraSocial/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            service.eliminarObraSocial(id);
            return new ResponseEntity<>("ObraSocial eliminada correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}
