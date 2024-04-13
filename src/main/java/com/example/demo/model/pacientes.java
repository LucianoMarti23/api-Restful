package com.example.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(unique = true , nullable = false)
    private Long id;


    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Apellido es requerido")
    @NotNull
    private String apellido;


    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Nombre es requerido")
    @NotNull
    private String nombre;

    @Column(length = 40 , nullable = false)
    @NotBlank(message = "Domicilio es requerido")
    @NotNull
    private String domicilio;

    @Column(length = 20, unique = true , nullable = false)
    @NotBlank(message = "Dni es requerido")
    @NotNull
    private int dniPaciente;

    @Column(length = 60 , nullable = false)
    @NotBlank(message = "Localidad es requerida")
    @NotNull
    private String localidad;

    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Provincia es requerido")
    @NotNull
    private String provincia;

    @Column(length = 20 , nullable = false)
    @NotBlank(message = "celular es requerido")
    @NotNull
    private int celular;


    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Telefono es requerido")
    @NotNull
    private int telefono;


    @Column(length = 40 , nullable = false)
    @NotBlank(message = "Email es requerido")
    @NotNull
    private String email;


}
