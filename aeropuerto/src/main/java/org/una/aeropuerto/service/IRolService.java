/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.RolesDTO;

/**
 *
 * @author Bosco
 */
public interface IRolService {
    
    public Optional<List<RolesDTO>> findAll();

    public Optional<RolesDTO> findById(Long id);
    
    public RolesDTO create(RolesDTO rol);

    public Optional<RolesDTO> update(RolesDTO rol, Long id);
    
    public Optional<List<RolesDTO>> findByEstado(boolean estado);
    
    public Optional<RolesDTO> findByCodigo(String nombreRol);
}
