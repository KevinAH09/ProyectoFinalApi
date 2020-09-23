/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AreaTrabajoDTO;

/**
 *
 * @author Bosco
 */
public interface IAreaTrabajoService {
    
    public Optional<List<AreaTrabajoDTO>> findAll();

    public Optional<AreaTrabajoDTO> findById(Long id);
    
    public Optional<List<AreaTrabajoDTO>> findByEstado(boolean estado);
    
    public Optional<List<AreaTrabajoDTO>> findByNombreAreaTrabajoContainingIgnoreCase(String nombreAreaTrabajo);
    
    public AreaTrabajoDTO create(AreaTrabajoDTO areaTrabajo);

    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO areaTrabajo, Long id);

//    public void delete(Long id);
//
//    public void deleteAll();
}
