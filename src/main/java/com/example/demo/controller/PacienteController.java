package com.example.demo.controller;


import com.example.demo.dto.PacienteDTO;
import com.example.demo.exceptions.CustomHandler;
import com.example.demo.exceptions.PacienteAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.serviceInterface.I_PacienteService;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Pacientes")
public class PacienteController {


    private final I_PacienteService service;


    @GetMapping("/listPaciente")
    public ResponseEntity<?> listPacientes() {
        try {
            return new ResponseEntity<>(service.getAllPacientes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR Interno en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/newPaciente")
    public ResponseEntity<?> add(@RequestBody PacienteDTO paciente) {
        try {
            service.guardarPaciente(paciente);
            return new ResponseEntity<>(paciente, HttpStatus.CREATED);
        } catch (PacienteAlreadyExist
                e) {
            // Handle the specific exception thrown by the service
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Handle other runtime exceptions, you might want to log them or return a generic error response
            return new ResponseEntity<>("Error at save Paciente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updatePaciente/{dni}")
    public ResponseEntity<?> updateProduct(@RequestBody PacienteDTO paciente, @PathVariable int dni) {
        try {
            String updatedPaciente = service.updatearPaciente(dni, paciente);
            return new ResponseEntity<>(updatedPaciente, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (CustomHandler e) {
            return new ResponseEntity<>("Error al actualizar Paciente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<String> deleteProduct(@PathVariable int dni) {
        try {
            service.eliminarPaciente(dni);
            return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> getPaciente(@PathVariable int dni) {

        try {
            return new ResponseEntity<>(service.getPacientebyDNI(dni) , HttpStatus.OK);

        } catch (ResponseStatusException e) {

                return new ResponseEntity<>(e.getReason() , e.getStatusCode());
                }
        }

    }




