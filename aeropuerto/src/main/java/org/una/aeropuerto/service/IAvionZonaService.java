/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AvionesZonasDTO;

/**
 *
 * @author Bosco
 */
public interface IAvionZonaService {
    public Optional<List<AvionesZonasDTO>> findAll();

    public Optional<AvionesZonasDTO> findById(Long id);
    
    public AvionesZonasDTO create(AvionesZonasDTO avionZona);

    public Optional<AvionesZonasDTO> update(AvionesZonasDTO avionZona, Long id);
    
    public Optional<List<AvionesZonasDTO>> findByAvion(Long avion);
    
    public Optional<List<AvionesZonasDTO>> findByZonaId(Long zona);
    
//    public void delete(Long id);
//
//    public void deleteAll();
}
