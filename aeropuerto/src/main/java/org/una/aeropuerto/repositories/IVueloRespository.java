/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.BitacoraVuelo;
import org.una.aeropuerto.entities.Vuelo;

/**
 *
 * @author Bosco
 */
public interface IVueloRespository extends JpaRepository<Vuelo, Long>{
    
    public List<Vuelo> findByEstadoContaining(boolean estado);
    
    public List<Vuelo> findByDestino(@Param("destino") String destino);
    
    public List<Vuelo> findByOrigen(@Param("origen") String origen);
    
    public List<Vuelo> findByAvionId(@Param("avion") Long avion);
    
    public List<Vuelo> findBybitacoraVueloId(@Param("bitacoraVueloId") Long bitacoraVueloId);
    
    

//    public List<Vuelo> findByZonaId(@Param("zona") Long zona);
}
