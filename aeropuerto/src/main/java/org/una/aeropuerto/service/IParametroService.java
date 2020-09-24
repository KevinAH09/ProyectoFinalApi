/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ParametroDTO;

/**
 *
 * @author Bosco
 */
public interface IParametroService {
    
    public Optional<List<ParametroDTO>> findAll();

    public Optional<ParametroDTO> findById(Long id);
    
    public ParametroDTO create(ParametroDTO parametro);

    public Optional<ParametroDTO> update(ParametroDTO parametro, Long id);
    
    public Optional<List<ParametroDTO>> findByEstado(boolean estado);
    
    public Optional<List<ParametroDTO>> findByNombreParametro(String nombreParametro);
    
    //FALTA MAS
}
