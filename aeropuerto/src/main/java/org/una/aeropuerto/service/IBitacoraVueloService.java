/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.BitacoraVueloDTO;

/**
 *
 * @author Bosco
 */
public interface IBitacoraVueloService {
    
    public Optional<List<BitacoraVueloDTO>> findAll();

    public Optional<BitacoraVueloDTO> findById(Long id);
    
    public Optional<List<BitacoraVueloDTO>> findByTipoBitacoraContainingIgnoreCase(String tipoBitacora);
    
    public BitacoraVueloDTO create(BitacoraVueloDTO bitacora);

    public Optional<BitacoraVueloDTO> update(BitacoraVueloDTO bitacora, Long id);
    
    public Optional<List<BitacoraVueloDTO>> findByCargaPasajero(boolean cargaPasajero);
    
    public Optional<List<BitacoraVueloDTO>> findByCargaCombustible(boolean cargaCombustible);
    
    public Optional<List<BitacoraVueloDTO>> findByZonaDescarga(boolean zonaDescarga);
    
    public Optional<List<BitacoraVueloDTO>> findByAutorizacionTorreControl(boolean autorizacionTorreControl);
    
}
