/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AerolineasDTO;

/**
 *
 * @author Bosco
 */
public interface IAerolineaService {
    
    public Optional<List<AerolineasDTO>> findAll();

    public Optional<AerolineasDTO> findById(Long id);

    public Optional<List<AerolineasDTO>> findByEstado(boolean estado);

    public Optional<List<AerolineasDTO>> findByNombreAerolineaContainingIgnoreCase(String nombreAerolinea);
    
    public Optional<List<AerolineasDTO>> findByNombreResponsableContainingIgnoreCase(String nombreResponsable);

    public AerolineasDTO create(AerolineasDTO aerolinea);

    public Optional<AerolineasDTO> update(AerolineasDTO aerolinea, Long id);

//    public void delete(Long id);
//
//    public void deleteAll();
}
