/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ControlesGastosDTO;
import org.una.aeropuerto.dto.DetallesControlesGastosDTO;

/**
 *
 * @author Bosco
 */
public interface IDetalleControlGastoService {
     public Optional<List<DetallesControlesGastosDTO>> findAll();

    public Optional<DetallesControlesGastosDTO> findById(Long id);
    
    public DetallesControlesGastosDTO create(DetallesControlesGastosDTO controlGastoDetalle);

    public Optional<DetallesControlesGastosDTO> update(DetallesControlesGastosDTO controlGastoDetalle, Long id);

    public Optional<List<DetallesControlesGastosDTO>> findByTipoServicio(String tipoServicio);
    
    public Optional<List<DetallesControlesGastosDTO>>  findByDuracion(Long duracion);
    
    public Optional<List<ControlesGastosDTO>> findByEstado(String estado);
    
    public Optional<List<ControlesGastosDTO>> findByEstadoPago(String estado);
    
    public Optional<List<ControlesGastosDTO>> findByAreaTrabajoId(Long id);
    
    
}
