/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.RegistrosAccionesDTO;

/**
 *
 * @author Bosco
 */
public interface IRegistroAccionService {
    
    public Optional<List<RegistrosAccionesDTO>> findAll();

    public Optional<RegistrosAccionesDTO> findById(Long id);
    
    public RegistrosAccionesDTO create(RegistrosAccionesDTO registroAccion);

    public Optional<RegistrosAccionesDTO> update(RegistrosAccionesDTO registroAccion, Long id);
    
    public Optional<List<RegistrosAccionesDTO>> findByUsuarioId(Long id);

    public Optional<List<RegistrosAccionesDTO>> findByFechaRegistro(Date fecha);

    public Optional<List<RegistrosAccionesDTO>> findByFechaRegistroBetween(Date startDate,Date endDate);
}
