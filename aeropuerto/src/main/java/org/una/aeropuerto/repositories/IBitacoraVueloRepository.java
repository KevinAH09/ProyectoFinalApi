/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.BitacorasVuelos;

/**
 *
 * @author Bosco
 */
public interface IBitacoraVueloRepository extends JpaRepository<BitacorasVuelos, Long>{
    
    public List<BitacorasVuelos> findByTipoBitacoraContainingIgnoreCase(@Param("TipoBitacora") String tipoBitacora);
    
    public List<BitacorasVuelos> findByCargaPasajero(boolean cargaPasajero);
    
    public List<BitacorasVuelos> findByCargaCombustible(boolean cargaCombustible);
    
    public List<BitacorasVuelos> findByZonaDescarga(boolean zonaDescarga);
    
    public List<BitacorasVuelos> findByAutorizacionTorreControl(boolean autorizacionTorreControl);
    
}
