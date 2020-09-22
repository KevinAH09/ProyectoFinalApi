/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AvionDTO;

/**
 *
 * @author Bosco
 */
public interface IAvionService {
    
    public Optional<List<AvionDTO>> findAll();

    public Optional<AvionDTO> findById(Long id);
    
    public AvionDTO create(AvionDTO avion);

    public Optional<AvionDTO> update(AvionDTO avion, Long id);
    
    public Optional<List<AvionDTO>> findByMatriculaContainingIgnoreCase(String matricula);
    
    public Optional<List<AvionDTO>> findBytipoAvionContainingIgnoreCase(String tipoAvion);
    
    public Optional<List<AvionDTO>> findByAerolineaId(Long id);

//    public void delete(Long id);
//
//    public void deleteAll();
}
