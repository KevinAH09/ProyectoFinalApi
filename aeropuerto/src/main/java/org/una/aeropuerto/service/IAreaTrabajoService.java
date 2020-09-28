/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AreasTrabajosDTO;

/**
 *
 * @author Bosco
 */
public interface IAreaTrabajoService {
    
    public Optional<List<AreasTrabajosDTO>> findAll();

    public Optional<AreasTrabajosDTO> findById(Long id);
    
    public Optional<List<AreasTrabajosDTO>> findByEstado(boolean estado);
    
    public Optional<List<AreasTrabajosDTO>> findByNombreAreaTrabajoContainingIgnoreCase(String nombreAreaTrabajo);
    
    public Optional<AreasTrabajosDTO> findByNombreAreaTrabajo(String nombreAreaTrabajo);
    
    public AreasTrabajosDTO create(AreasTrabajosDTO areaTrabajo);

    public Optional<AreasTrabajosDTO> update(AreasTrabajosDTO areaTrabajo, Long id);

//    public void delete(Long id);
//
//    public void deleteAll();
}
