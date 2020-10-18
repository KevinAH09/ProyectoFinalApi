/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.ControlesGastosDTO;
import org.una.aeropuerto.dto.DetallesControlesGastosDTO;
import org.una.aeropuerto.entities.DetallesControlesGastos;
import org.una.aeropuerto.repositories.IDetalleControlGastoRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class DetalleControlGastoServiceImplementation implements IDetalleControlGastoService {

    @Autowired
    private IDetalleControlGastoRepository DetalleControlGastoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DetallesControlesGastosDTO>> findAll() {
        return (Optional<List<DetallesControlesGastosDTO>>) ConversionLista.findList((DetalleControlGastoRepository.findAll()), DetallesControlesGastosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetallesControlesGastosDTO> findById(Long id) {
        return (Optional<DetallesControlesGastosDTO>) ConversionLista.oneToDto(DetalleControlGastoRepository.findById(id), DetallesControlesGastosDTO.class);
    }

    @Override
    @Transactional
    public DetallesControlesGastosDTO create(DetallesControlesGastosDTO controlGastoDetalle) {
        DetallesControlesGastos user = MapperUtils.EntityFromDto(controlGastoDetalle, DetallesControlesGastos.class);
        user = DetalleControlGastoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, DetallesControlesGastosDTO.class);
    }

    @Override
    @Transactional
    public Optional<DetallesControlesGastosDTO> update(DetallesControlesGastosDTO controlGastoDetalle, Long id) {
        if (DetalleControlGastoRepository.findById(id).isPresent()) {
            DetallesControlesGastos user = MapperUtils.EntityFromDto(controlGastoDetalle, DetallesControlesGastos.class);
            user = DetalleControlGastoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, DetallesControlesGastosDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DetallesControlesGastosDTO>> findByTipoServicio(String tipoServicio) {
        return (Optional<List<DetallesControlesGastosDTO>>) ConversionLista.findList(DetalleControlGastoRepository.findByTipoServicio(tipoServicio), DetallesControlesGastosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DetallesControlesGastosDTO>> findByDuracion(Long duracion) {
        return (Optional<List<DetallesControlesGastosDTO>>) ConversionLista.findList(DetalleControlGastoRepository.findByDuracion(duracion), DetallesControlesGastosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ControlesGastosDTO>> findByEstado(String estado) {
        return (Optional<List<ControlesGastosDTO>>) ConversionLista.findList(Optional.ofNullable(DetalleControlGastoRepository.findByEstado(estado)), ControlesGastosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ControlesGastosDTO>> findByAreaTrabajoId(Long id) {
        return (Optional<List<ControlesGastosDTO>>) ConversionLista.findList(DetalleControlGastoRepository.findByAreaTrabajoId(id), ControlesGastosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ControlesGastosDTO>> findByEstadoPago(String estado) {
        return (Optional<List<ControlesGastosDTO>>) ConversionLista.findList(DetalleControlGastoRepository.findByEstadoPago(estado), ControlesGastosDTO.class);
    }

}
