package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class PersonaDTO {

    private String nombre;
    private String apellido;
    private int celular;
    private String email;


}

