/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.BitacoraVuelo;
import org.una.aeropuerto.entities.Vuelo;

/**
 *
 * @author Bosco
 */
public interface IVueloRepository extends JpaRepository<Vuelo, Long>{
    
    public List<Vuelo> findByEstado(boolean estado);
    
    public List<Vuelo> findByDestino(@Param("destino") String destino);
    
    public List<Vuelo> findByOrigen(@Param("origen") String origen);
    
    @Query("SELECT u FROM Vuelo u LEFT JOIN u.avionId d WHERE  d.id=:id")
    public List<Vuelo> findByAvionId(Long id);
    
    @Query("SELECT u FROM Vuelo u LEFT JOIN u.bitacoraVueloId d WHERE  d.id=:id")
    public List<Vuelo> findBybitacoraVueloId(Long id);
    
    

//    public List<Vuelo> findByZonaId(@Param("zona") Long zona);
}
