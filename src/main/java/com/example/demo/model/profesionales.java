package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class profesionales {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    private Long id;


    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Nombre es requerido")
    @NotNull
    private String nombre;


    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Apellido es requerido")
    @NotNull
    private String apellido;


    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Matricula es requerida")
    @NotNull
    private String matricula;

    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Titulo Profesional es requerido")
    @NotNull
    private String titulo_profesional;


    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Email  es requerido")
    @NotNull
    private String email;

    @Column(length = 20 , nullable = false)
    @NotBlank(message = "celular Profesional es requerido")
    @NotNull
    private int celular;

    @Column(length = 20 , nullable = false)
    @NotBlank(message = "dni_profesional Profesional es requerido")
    @NotNull
    private int dniProfesional;


}
