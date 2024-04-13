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
public class turnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    private Long id_turno;


    @ManyToOne
    @JoinColumn(name = "id_profesional", referencedColumnName = "id")
    private profesionales profesional;

    @ManyToOne
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id")
    private especialidades especialidad;



    @NotBlank(message = "Fecha es requerida")
    @NotNull
    @Column(nullable = false)
    private Date fecha;

    @NotBlank(message = "Apellido es requerido")
    @NotNull
    @Column(nullable = false)
    private String apellido_paciente;

    @NotBlank(message = "Nombre es requerido")
    @NotNull
    @Column(nullable = false)
    private String nombre_paciente;


    @NotBlank(message = "Dni es requerido")
    @NotNull
    @Column(nullable = false)
    private int dni_paciente;


    @NotBlank(message = "Obra_social es requerido")
    @NotNull
    @Column(nullable = false)
    private int obra_social;


    @NotBlank(message = "Motivo es requerido")
    @NotNull
    @Column(nullable = false , length = 50)
    private String motivo_turno;



    @NotBlank(message = "Diagnostico es requerido")
    @NotNull
    @Column(nullable = false , length = 100)
    private String diagnostico;

}
