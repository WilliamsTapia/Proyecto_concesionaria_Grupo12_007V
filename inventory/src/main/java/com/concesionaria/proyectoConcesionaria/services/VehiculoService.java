package com.concesionaria.proyectoConcesionaria.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.concesionaria.proyectoConcesionaria.dto.request.VehiculoRequestDTO;
import com.concesionaria.proyectoConcesionaria.dto.response.VehiculoResponseDTO;
import com.concesionaria.proyectoConcesionaria.models.VehiculoModel;
import com.concesionaria.proyectoConcesionaria.repositories.VehiculoRepository;
import jakarta.transaction.Transactional;
import com.concesionaria.proyectoConcesionaria.exceptions.NotFoundException;

@Service
@Transactional
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService (VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<VehiculoResponseDTO> obtenerTodos() {
        return vehiculoRepository.findAll()
                .stream()
                .map(this::mapToResponseVehiculo)
                .toList();
    }
    
    private VehiculoResponseDTO mapToResponseVehiculo(VehiculoModel vehiculo) {
        return VehiculoResponseDTO.builder()
                .id(vehiculo.getId())
                .marca(vehiculo.getMarca())
                .modelo(vehiculo.getModelo())
                .anio(vehiculo.getAnio()) 
                .patente(vehiculo.getPatente())
                .precio(vehiculo.getPrecio())
                .estado(vehiculo.getEstado())
                .build();
    }

    public VehiculoResponseDTO guardar(VehiculoRequestDTO request) {
        if (vehiculoRepository.existsByPatente(request.getPatente())) {
            throw new RuntimeException("La patente ya existe");
        }
        VehiculoModel vehiculo = VehiculoModel.builder()
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .anio(request.getAnio())
                .patente(request.getPatente())
                .estado(request.getEstado())
                .precio(request.getPrecio())
                .build();

        VehiculoModel guardado = vehiculoRepository.save(vehiculo);
        return mapToResponseVehiculo(guardado);
    }

    public VehiculoResponseDTO obtenerPorId(Long id) {
        VehiculoModel vehiculo = vehiculoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("No existe el vehículo con id: " + id));

         return mapToResponseVehiculo(vehiculo);
    }

    public void eliminar(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new NotFoundException("No se puede eliminar: el vehículo con ID " + id + " no existe en la base de datos.");
        }
            vehiculoRepository.deleteById(id);
    }

    public VehiculoResponseDTO actualizar(Long id, VehiculoRequestDTO request) {
    VehiculoModel vehiculoExistente = vehiculoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("No se puede actualizar: id " + id + " no encontrado"));

    if (!vehiculoExistente.getPatente().equals(request.getPatente()) && 
         vehiculoRepository.existsByPatente(request.getPatente())) {
        throw new RuntimeException("Error: La nueva patente " + request.getPatente() + " ya está registrada en otro vehículo.");
    }

        vehiculoExistente.setMarca(request.getMarca());
        vehiculoExistente.setModelo(request.getModelo());
        vehiculoExistente.setAnio(request.getAnio()); 
        vehiculoExistente.setPrecio(request.getPrecio());
        vehiculoExistente.setEstado(request.getEstado());
        vehiculoExistente.setPatente(request.getPatente());

        
        VehiculoModel actualizado = vehiculoRepository.save(vehiculoExistente);
        return mapToResponseVehiculo(actualizado);
    }   

    public VehiculoResponseDTO obtenerPorPatente(String patente) {
        VehiculoModel pVehiculo = vehiculoRepository.findByPatente(patente)
            .orElseThrow(() -> new NotFoundException("No existe el vehículo con patente: " + patente));

         return mapToResponseVehiculo(pVehiculo);
    }

    public List<VehiculoResponseDTO> listarPorEstado(String estado) {

    List<VehiculoModel> listaModelos = vehiculoRepository.findAllByEstado(estado);
    return listaModelos.stream()
            .map(this::mapToResponseVehiculo)
            .toList();
    }
    public List<VehiculoResponseDTO> listarPorMarca(String marca) {
    return vehiculoRepository.findAllByMarcaIgnoreCase(marca)
            .stream()
            .map(this::mapToResponseVehiculo)
            .toList();
    }   

    public List<VehiculoResponseDTO> listarPorRangoPrecio(Integer min, Integer max) {
    return vehiculoRepository.findByPrecioBetween(min, max)
            .stream()
            .map(this::mapToResponseVehiculo)
            .toList();
}
}