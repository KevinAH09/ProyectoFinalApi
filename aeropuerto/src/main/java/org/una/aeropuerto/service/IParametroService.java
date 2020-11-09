/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ParametrosDTO;

/**
 *
 * @author Bosco
 */
public interface IParametroService {
    
    public Optional<List<ParametrosDTO>> findAll();

    public Optional<ParametrosDTO> findById(Long id);
    
    public ParametrosDTO create(ParametrosDTO parametro);

    public Optional<ParametrosDTO> update(ParametrosDTO parametro, Long id);
    
    public Optional<List<ParametrosDTO>> findByEstado(boolean estado);
    
    public Optional<ParametrosDTO> findByNombreParametro(String nombreParametro);
    
    //FALTA MAS
}
