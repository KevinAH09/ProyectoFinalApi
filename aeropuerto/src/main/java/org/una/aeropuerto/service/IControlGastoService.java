/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ControlesGastosDTO;

/**
 *
 * @author Bosco
 */
public interface IControlGastoService {

    public Optional<List<ControlesGastosDTO>> findAll();

    public Optional<ControlesGastosDTO> findById(Long id);

    public ControlesGastosDTO create(ControlesGastosDTO controlGasto);

    public Optional<ControlesGastosDTO> update(ControlesGastosDTO controlGasto, Long id);

    public Optional<List<ControlesGastosDTO>> findByEmpresaContratanteContainingIgnoreCase(String empresa);

    public Optional<ControlesGastosDTO> findByNumeroContrato(String numeroContrato);

    public Optional<List<ControlesGastosDTO>> findByEstadoPago(String estado);

    public Optional<List<ControlesGastosDTO>> findByTipoServicio(String tipo);

    public Optional<List<ControlesGastosDTO>> findByFechaRegistro(Date fecha);

    public Optional<List<ControlesGastosDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Optional<ControlesGastosDTO> findByDetalleControlGastoId(Long id);
}
