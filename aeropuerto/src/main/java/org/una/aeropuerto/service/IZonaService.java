/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ZonasDTO;

/**
 *
 * @author Bosco
 */
public interface IZonaService {
    public Optional<List<ZonasDTO>> findAll();

    public Optional<ZonasDTO> findById(Long id);
    
    public ZonasDTO create(ZonasDTO zona);

    public Optional<ZonasDTO> update(ZonasDTO zona, Long id);
    
    public Optional<List<ZonasDTO>> findByEstado(boolean estado);
    
    public Optional<List<ZonasDTO>> findByNombreZonaContainingIgnoreCase(String nombreZona);

    public Optional<List<ZonasDTO>> findByCodigo(String codigo);
}
