/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AerolineaDTO;

/**
 *
 * @author Bosco
 */
public interface IAerolineaService {
    
    public Optional<List<AerolineaDTO>> findAll();

    public Optional<AerolineaDTO> findById(Long id);

    public Optional<List<AerolineaDTO>> findByEstado(boolean estado);

    public Optional<List<AerolineaDTO>> findByNombreAerolineaContainingIgnoreCase(String nombreAerolinea);
    
    public Optional<List<AerolineaDTO>> findByNombreResponsableContainingIgnoreCase(String nombreResponsable);

    public AerolineaDTO create(AerolineaDTO aerolinea);

    public Optional<AerolineaDTO> update(AerolineaDTO aerolinea, Long id);

//    public void delete(Long id);
//
//    public void deleteAll();
}
