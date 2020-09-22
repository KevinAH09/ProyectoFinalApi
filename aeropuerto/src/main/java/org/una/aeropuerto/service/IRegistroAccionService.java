/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.RegistroAccionDTO;

/**
 *
 * @author Bosco
 */
public interface IRegistroAccionService {
    public Optional<List<RegistroAccionDTO>> findAll();

    public Optional<RegistroAccionDTO> findById(Long id);
    
    public RegistroAccionDTO create(RegistroAccionDTO registroAccion);

    public Optional<RegistroAccionDTO> update(RegistroAccionDTO registroAccion, Long id);
    
    public Optional<List<RegistroAccionDTO>> findByUsuarioId(Long id);

    public Optional<List<RegistroAccionDTO>> findByFechaRegistro(Date fecha);

    public Optional<List<RegistroAccionDTO>> findByFechaRegistroBetween(Date startDate,Date endDate);
}
