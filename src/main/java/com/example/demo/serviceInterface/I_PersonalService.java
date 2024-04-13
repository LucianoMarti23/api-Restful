package com.example.demo.serviceInterface;


import com.example.demo.model.personales;

import java.util.List;
import java.util.Optional;

public interface I_PersonalService {
    List<personales> getAllPersonales();

    Optional<personales> getPersonalbyID(Long id);


    personales guardarPersonal(personales personal , Long id_cargo);


    personales updatearPersonales(Long id , personales nuevoPersonal);

    void eliminarPersonal(Long id);
}
