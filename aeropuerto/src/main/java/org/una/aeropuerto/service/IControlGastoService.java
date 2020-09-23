/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ControlGastoDTO;

/**
 *
 * @author Bosco
 */
public interface IControlGastoService {
    
    
    public Optional<List<ControlGastoDTO>> findAll();

    public Optional<ControlGastoDTO> findById(Long id);
    
    public ControlGastoDTO create(ControlGastoDTO controlGasto);

    public Optional<ControlGastoDTO> update(ControlGastoDTO controlGasto, Long id);
    
    public Optional<List<ControlGastoDTO>> findByEmpresaContratanteContainingIgnoreCase(String empresa);
    
    public Optional<ControlGastoDTO> findByNumeroContrato(String numeroContrato);
    
    public Optional<List<ControlGastoDTO>> findByEstado(String estado);
    
    public Optional<List<ControlGastoDTO>> findByEstadoPago(String estado);
            
    public Optional<List<ControlGastoDTO>> findByAreaTrabajoId(Long id);
    
    public Optional<List<ControlGastoDTO>> findByFechaRegistro(Date fecha);
    
    public Optional<List<ControlGastoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
}
