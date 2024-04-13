package com.example.demo.controller;


import com.example.demo.exceptions.CustomHandler;
import com.example.demo.model.personales;
import com.example.demo.serviceInterface.I_PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Personales")
public class PersonalController {

    private final I_PersonalService service;


    @GetMapping("/listCargos")
    public ResponseEntity<?> listPersonales() {
        try {
            return new ResponseEntity<>(service.getAllPersonales() , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Personales not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonalbyID(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(service.getPersonalbyID(id) , HttpStatus.OK);

        } catch (ResponseStatusException e) {

            return new ResponseEntity<>(e.getReason() , e.getStatusCode());
        }





    }
    @PostMapping("/guardarPersonal")
    public ResponseEntity<?> guardarPersonal(@RequestBody personales personal, @RequestParam Long cargoId) {
        try {
            personales savedPersonal = service.guardarPersonal(personal, cargoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonal);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
    @PatchMapping("/updatePersonal")
    public ResponseEntity<?> updateCargo(@RequestBody personales personal) {
        try {
            personales updatePersonal = service.updatearPersonales(personal.getId(), personal);
            return new ResponseEntity<>(updatePersonal, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (CustomHandler e) {
            return new ResponseEntity<>("Error al actualizar Personal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePersonal(@PathVariable Long id) {
        try {
            service.eliminarPersonal(id);
            return new ResponseEntity<>("Personal eliminado correctamente", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }


}






