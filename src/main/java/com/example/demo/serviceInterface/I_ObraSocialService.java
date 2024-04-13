package com.example.demo.serviceInterface;

import com.example.demo.model.obra_sociales;


import java.util.List;
import java.util.Optional;



public interface I_ObraSocialService {

    List<obra_sociales> getAllObraSocial();

    Optional<obra_sociales> getObraSocialbyID(Long id);


    obra_sociales guardarObraSocial(obra_sociales obra_social);


    obra_sociales updatearObraSocial(Long id , obra_sociales nuevaObraSocial);

    void eliminarObraSocial(Long id);
}
