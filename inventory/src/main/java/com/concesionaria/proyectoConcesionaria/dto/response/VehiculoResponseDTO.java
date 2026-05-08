package com.concesionaria.proyectoConcesionaria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String anio; 
    private String patente;
    private Integer precio; 
    private String estado;
}