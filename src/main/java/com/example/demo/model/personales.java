package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class personales {

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

    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Fecha es requerida")
    @NotNull
    private Date fecha_inicio;

    @Column(length = 20 , nullable = false)
    @NotBlank(message = "Fecha es requerida")
    @NotNull
    private Date fecha_fin;


    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false)
    private cargos typeCargo;




}
