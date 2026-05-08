package com.concesionaria.proyectoConcesionaria.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoRequestDTO {
    private Long id;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotBlank(message = "El año es obligatorio")
    @Size(min = 4, max = 4, message = "El año debe tener 4 dígitos")
    private String anio; 

    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private Integer precio; 
}