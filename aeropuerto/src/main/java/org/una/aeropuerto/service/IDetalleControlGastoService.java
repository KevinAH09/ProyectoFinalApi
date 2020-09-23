/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.DetalleControlGastoDTO;

/**
 *
 * @author Bosco
 */
public interface IDetalleControlGastoService {
     public Optional<List<DetalleControlGastoDTO>> findAll();

    public Optional<DetalleControlGastoDTO> findById(Long id);
    
    public DetalleControlGastoDTO create(DetalleControlGastoDTO controlGastoDetalle);

    public Optional<DetalleControlGastoDTO> update(DetalleControlGastoDTO controlGastoDetalle, Long id);

    public Optional<List<DetalleControlGastoDTO>> findByTipoServicio(String tipoServicio);
    
    public Optional<List<DetalleControlGastoDTO>>  findByDuracion(Long duracion);
}
