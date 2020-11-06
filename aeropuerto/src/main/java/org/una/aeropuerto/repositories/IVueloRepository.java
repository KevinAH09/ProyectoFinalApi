/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Vuelos;

/**
 *
 * @author Bosco
 */
public interface IVueloRepository extends JpaRepository<Vuelos, Long>{
    
    public List<Vuelos> findByEstado(boolean estado);
    
    public List<Vuelos> findByDestino(@Param("destino") String destino);
    
    public List<Vuelos> findByOrigen(@Param("origen") String origen);
    
    @Query("SELECT u FROM Vuelos u LEFT JOIN u.avionId d WHERE  d.id=:id")
    public List<Vuelos> findByAvionId(Long id);
    
    @Query("SELECT u FROM Vuelos u LEFT JOIN u.bitacoraVueloId d WHERE  d.id=:id")
    public List<Vuelos> findBybitacoraVueloId(Long id);
    
    public List<Vuelos> findByFechaInicio(@Param("fechaInicio") Date fechaInicio);
}
