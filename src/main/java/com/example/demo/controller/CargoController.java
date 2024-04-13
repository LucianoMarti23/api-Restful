package com.example.demo.controller;


import com.example.demo.exceptions.CustomHandler;
import com.example.demo.exceptions.PacienteAlreadyExist;
import com.example.demo.model.cargos;
import com.example.demo.serviceInterface.I_CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Cargos")
public class CargoController {
    private final I_CargoService service;


    @GetMapping("/listCargos")
    public ResponseEntity<?> listCargos() {
        try {
            return new ResponseEntity<>(service.getAllCargos() , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cargos not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCargo(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(service.getCargobyID(id) , HttpStatus.OK);

        } catch (ResponseStatusException e) {

            return new ResponseEntity<>(e.getReason() , e.getStatusCode());
        }
    }



    @PostMapping("/newEspecialidad")
    public ResponseEntity<?> add(@RequestBody cargos cargo) {
        try {
            service.guardarCargo(cargo);
            return new ResponseEntity<>(cargo, HttpStatus.CREATED);
        } catch (PacienteAlreadyExist
                e) {
            // Handle the specific exception thrown by the service
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            // Handle other runtime exceptions, you might want to log them or return a generic error response
            return new ResponseEntity<>("Error at save Cargo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateCargo")
    public ResponseEntity<?> updateCargo(@RequestBody cargos cargo) {
        try {
            cargos updatedCargo = service.updatearCargo(cargo.getId(), cargo);
            return new ResponseEntity<>(updatedCargo, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (CustomHandler e) {
            return new ResponseEntity<>("Error al actualizar Cargo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/deleteCargo/{id}")
    public ResponseEntity<String> deleteCargo(@PathVariable Long id) {
        try {
            service.eliminarCargo(id);
            return new ResponseEntity<>("Cargo eliminada correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }






}
