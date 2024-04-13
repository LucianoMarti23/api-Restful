package com.example.demo.serviceInterface;

import com.example.demo.model.cargos;
import com.example.demo.model.especialidades;

import java.util.List;
import java.util.Optional;

public interface I_CargoService {

    List<cargos> getAllCargos();

    Optional<cargos> getCargobyID(Long id);


    cargos guardarCargo(cargos cargo);


    cargos updatearCargo(Long id , cargos nuevaCargo);

    void eliminarCargo(Long id);

}
