package com.concesionaria.proyectoConcesionaria.controllers;

import com.concesionaria.proyectoConcesionaria.dto.request.VehiculoRequestDTO;
import com.concesionaria.proyectoConcesionaria.dto.response.VehiculoResponseDTO;
import com.concesionaria.proyectoConcesionaria.services.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // GET /api/v1/vehiculos
    // Lista todos los vehículos
    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> listar() {
        return ResponseEntity.ok(vehiculoService.obtenerTodos());
    }

    // GET /api/v1/vehiculos/{id}
    // Busca un vehículo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.obtenerPorId(id));
    }

    // POST /api/v1/vehiculos
    // Crea un nuevo vehículo validando el request
    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> guardar(@Valid @RequestBody VehiculoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.guardar(request));
    }

    // PUT /api/v1/vehiculos/{id}
    // Actualiza un vehículo existente
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> actualizar( @PathVariable Long id, @Valid @RequestBody VehiculoRequestDTO request) {
        return ResponseEntity.ok(vehiculoService.actualizar(id, request));
    }

    // DELETE /api/v1/vehiculos/{id}
    // Elimina un vehículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<VehiculoResponseDTO> obtenerPorPatente(@PathVariable String patente) {
        return ResponseEntity.ok(vehiculoService.obtenerPorPatente(patente));
    }

    // GET /api/v1/vehiculos/estado/{estado}
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarPorEstado(@PathVariable String estado) {
        List<VehiculoResponseDTO> vehiculos = vehiculoService.listarPorEstado(estado);
        return ResponseEntity.ok(vehiculos);
    }

    // GET /api/v1/vehiculos/marca/{marca}
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(vehiculoService.listarPorMarca(marca));
    }

    // GET /api/v1/vehiculos/rango-precio/{min}/{max}
    @GetMapping("/rango-precio/{min}/{max}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarPorRangoPrecio(
            @PathVariable Integer min, 
            @PathVariable Integer max) {
        return ResponseEntity.ok(vehiculoService.listarPorRangoPrecio(min, max));
    }
}
