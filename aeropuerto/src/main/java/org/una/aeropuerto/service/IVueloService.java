/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.VueloDTO;

/**
 *
 * @author Bosco
 */
public interface IVueloService {
    public Optional<List<VueloDTO>> findAll();

    public Optional<VueloDTO> findById(Long id);
    
    public VueloDTO create(VueloDTO vuelo);

    public Optional<VueloDTO> update(VueloDTO vuelo, Long id);
    
    public Optional<List<VueloDTO>> findByEstadoContaining(boolean estado);
    
    public Optional<List<VueloDTO>> findByDestino(String destino);
    
    public Optional<List<VueloDTO>> findByOrigen(String origen);
    
    public Optional<List<VueloDTO>> findByAvionId(Long avion);
    
    public Optional<List<VueloDTO>> findBybitacoraVueloId(Long bitacora);
}
