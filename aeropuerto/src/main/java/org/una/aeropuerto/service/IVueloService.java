/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.VuelosDTO;

/**
 *
 * @author Bosco
 */
public interface IVueloService {
    public Optional<List<VuelosDTO>> findAll();

    public Optional<VuelosDTO> findById(Long id);
    
    public VuelosDTO create(VuelosDTO vuelo);

    public Optional<VuelosDTO> update(VuelosDTO vuelo, Long id);
    
    public Optional<List<VuelosDTO>> findByEstado(boolean estado);
    
    public Optional<List<VuelosDTO>> findByDestino(String destino);
    
    public Optional<List<VuelosDTO>> findByOrigen(String origen);
    
    public Optional<List<VuelosDTO>> findByAvionId(Long avion);
    
    public Optional<List<VuelosDTO>> findBybitacoraVueloId(Long bitacora);
    
    public Optional<List<VuelosDTO>> findByFechaInicio(Date fechaInicio);
}
