package com.concesionaria.proyectoConcesionaria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vehiculos")
public class VehiculoModel {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotBlank(message = "El año es obligatorio")
    private String anio; // Coincide con tu VARCHAR(4)

    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0)
    private Integer precio; 
}