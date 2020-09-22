/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.RolDTO;

/**
 *
 * @author Bosco
 */
public interface IRolService {
    
    public Optional<List<RolDTO>> findAll();

    public Optional<RolDTO> findById(Long id);
    
    public RolDTO create(RolDTO rol);

    public Optional<RolDTO> update(RolDTO rol, Long id);
    
    public Optional<List<RolDTO>> findByEstado(boolean estado);
    
    public Optional<RolDTO> findByCodigo(String nombreRol);
}
