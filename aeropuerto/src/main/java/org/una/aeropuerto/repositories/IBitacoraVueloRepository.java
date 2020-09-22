/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.AvionZona;
import org.una.aeropuerto.entities.BitacoraVuelo;

/**
 *
 * @author Bosco
 */
public interface IBitacoraVueloRepository extends JpaRepository<BitacoraVuelo, Long>{
    
    public List<BitacoraVuelo> findByTipoBitacoraContainingIgnoreCase(@Param("TipoBitacora") String tipoBitacora);
    
    public List<AvionZona> findByCargaPasajero(@Param("cargaPasajero") boolean cargaPasajero);
    
    public List<AvionZona> findByCargaCombustible(@Param("CargaCombustible") boolean cargaCombustible);
    
    public List<AvionZona> findByZonaDescarga(@Param("ZonaDescarga") boolean zonaDescarga);
    
    public List<AvionZona> findByAutorizacionTorreControl(@Param("autorizacionTorreControl") boolean autorizacionTorreControl);
    
}
