/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AvionesDTO;

/**
 *
 * @author Bosco
 */
public interface IAvionService {
    
    public Optional<List<AvionesDTO>> findAll();

    public Optional<AvionesDTO> findById(Long id);
    
    public AvionesDTO create(AvionesDTO avion);

    public Optional<AvionesDTO> update(AvionesDTO avion, Long id);
    
    public Optional<AvionesDTO> findByMatricula(String matricula);
    
    public Optional<List<AvionesDTO>> findByMatriculaContainingIgnoreCase(String matricula);
    
    public Optional<List<AvionesDTO>> findBytipoAvionContainingIgnoreCase(String tipoAvion);
    
    public Optional<List<AvionesDTO>> findByAerolineaId(Long id);
    
    public Optional<List<AvionesDTO>> findByEstado(boolean estado);
    
   // public Optional<List<AvionesDTO>> findByAerolineaIdMatricula(Long id,String Matricula);

//    public void delete(Long id);
//
//    public void deleteAll();
}
