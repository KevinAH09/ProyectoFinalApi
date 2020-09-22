/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ZonaDTO;

/**
 *
 * @author Bosco
 */
public interface IZonaService {
    public Optional<List<ZonaDTO>> findAll();

    public Optional<ZonaDTO> findById(Long id);
    
    public ZonaDTO create(ZonaDTO zona);

    public Optional<ZonaDTO> update(ZonaDTO zona, Long id);
    
    public Optional<List<ZonaDTO>> findByEstadoContaining(boolean estado);
    
    public Optional<List<ZonaDTO>> findByNombreZonaContainingIgnoreCase(String nombreZona);

    public Optional<List<ZonaDTO>> findByCodigo(String codigo);
}
