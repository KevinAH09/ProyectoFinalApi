/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AvionZonaDTO;

/**
 *
 * @author Bosco
 */
public interface IAvionZonaService {
    public Optional<List<AvionZonaDTO>> findAll();

    public Optional<AvionZonaDTO> findById(Long id);
    
    public AvionZonaDTO create(AvionZonaDTO avionZona);

    public Optional<AvionZonaDTO> update(AvionZonaDTO avionZona, Long id);
    
    public Optional<List<AvionZonaDTO>> findByAvion(Long avion);
    
    public Optional<List<AvionZonaDTO>> findByZonaId(Long zona);
    
//    public void delete(Long id);
//
//    public void deleteAll();
}
