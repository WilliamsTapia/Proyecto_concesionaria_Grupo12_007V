package com.concesionaria.proyectoConcesionaria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concesionaria.proyectoConcesionaria.models.VehiculoModel;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoModel, Long>  {


    Optional<VehiculoModel> findByPatente(String patente);

    boolean existsByPatente(String patente);

    List<VehiculoModel> findByEstado(String estado);

    List<VehiculoModel> findAllByEstado(String estado);

    List<VehiculoModel> findAllByMarcaIgnoreCase(String marca);
    
    List<VehiculoModel> findByPrecioBetween(Integer min, Integer max);
}

