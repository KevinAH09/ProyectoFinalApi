/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.dto.ControlGastoDTO;
import org.una.aeropuerto.entities.ControlGasto;
import org.una.aeropuerto.repositories.IControlGastoRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class ControlGastoServiceImplementation implements IControlGastoService {

    @Autowired
    private IControlGastoRepository ControlGastoRepository;

    @Override
    public Optional<List<ControlGastoDTO>> findAll() {
        return (Optional<List<ControlGastoDTO>>) ConversionLista.findList((ControlGastoRepository.findAll()), ControlGastoDTO.class);
    }

    @Override
    public Optional<ControlGastoDTO> findById(Long id) {
        return (Optional<ControlGastoDTO>) ConversionLista.oneToDto(ControlGastoRepository.findById(id), ControlGastoDTO.class);
    }

    @Override
    public ControlGastoDTO create(ControlGastoDTO controlGasto) {
        ControlGasto user = MapperUtils.EntityFromDto(controlGasto, ControlGasto.class);
        user = ControlGastoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ControlGastoDTO.class);
    }

    @Override
    public Optional<ControlGastoDTO> update(ControlGastoDTO controlGasto, Long id) {
        if (ControlGastoRepository.findById(id).isPresent()) {
            ControlGasto user = MapperUtils.EntityFromDto(controlGasto, ControlGasto.class);
            user = ControlGastoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ControlGastoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<ControlGastoDTO>> findByEmpresaContratanteContainingIgnoreCase(String empresa) {
        return (Optional<List<ControlGastoDTO>>)ConversionLista.findList(ControlGastoRepository.findByEmpresaContratanteContainingIgnoreCase(empresa),ControlGastoDTO.class);
    }

    @Override
    public Optional<ControlGastoDTO> findByNumeroContrato(String numeroContrato) {
return (Optional<ControlGastoDTO>) ConversionLista.oneToDto(Optional.ofNullable(ControlGastoRepository.findByNumeroContrato(numeroContrato)), ControlGastoDTO.class);    }

    @Override
    public Optional<List<ControlGastoDTO>> findByEstado(String estado) {
        return (Optional<List<ControlGastoDTO>>)ConversionLista.findList(Optional.ofNullable(ControlGastoRepository.findByEstado(estado)),ControlGastoDTO.class);
    }

    @Override
    public Optional<List<ControlGastoDTO>> findByAreaTrabajoId(Long id) {
        return (Optional<List<ControlGastoDTO>>) ConversionLista.findList(ControlGastoRepository.findByAreaTrabajoId(id),ControlGastoDTO.class);
    }

    @Override
    public Optional<List<ControlGastoDTO>> findByFechaRegistro(Date fecha) {
        return (Optional<List<ControlGastoDTO>>)ConversionLista.findList(Optional.ofNullable(ControlGastoRepository.findByFechaRegistro(fecha)),ControlGastoDTO.class);
    }

    @Override
    public Optional<List<ControlGastoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return (Optional<List<ControlGastoDTO>>)ConversionLista.findList(Optional.ofNullable(ControlGastoRepository.findByFechaRegistroBetween(startDate, endDate)),ControlGastoDTO.class);
    }

}
