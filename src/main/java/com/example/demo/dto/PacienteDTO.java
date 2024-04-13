package com.example.demo.dto;



import lombok.*;
@Setter
@Getter
@NoArgsConstructor
public class PacienteDTO extends PersonaDTO {

    private int dniPaciente;

    private String domicilio;

    private String localidad;

    private String provincia;

    private int telefono;


}






