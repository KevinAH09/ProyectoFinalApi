/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.BitacorasVuelosDTO;

/**
 *
 * @author Bosco
 */
public interface IBitacoraVueloService {
    
    public Optional<List<BitacorasVuelosDTO>> findAll();

    public Optional<BitacorasVuelosDTO> findById(Long id);
    
    public Optional<List<BitacorasVuelosDTO>> findByTipoBitacoraContainingIgnoreCase(String tipoBitacora);
    
    public BitacorasVuelosDTO create(BitacorasVuelosDTO bitacora);

    public Optional<BitacorasVuelosDTO> update(BitacorasVuelosDTO bitacora, Long id);
    
    public Optional<List<BitacorasVuelosDTO>> findByCargaPasajero(boolean cargaPasajero);
    
    public Optional<List<BitacorasVuelosDTO>> findByCargaCombustible(boolean cargaCombustible);
    
    public Optional<List<BitacorasVuelosDTO>> findByZonaDescarga(boolean zonaDescarga);
    
    public Optional<List<BitacorasVuelosDTO>> findByAutorizacionTorreControl(boolean autorizacionTorreControl);
    
}
