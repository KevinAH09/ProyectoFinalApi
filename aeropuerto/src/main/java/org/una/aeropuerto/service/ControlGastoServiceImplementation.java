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
import org.una.aeropuerto.dto.ControlesGastosDTO;
import org.una.aeropuerto.entities.ControlesGastos;
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
    public Optional<List<ControlesGastosDTO>> findAll() {
        return (Optional<List<ControlesGastosDTO>>) ConversionLista.findList((ControlGastoRepository.findAll()), ControlesGastosDTO.class);
    }

    @Override
    public Optional<ControlesGastosDTO> findById(Long id) {
        return (Optional<ControlesGastosDTO>) ConversionLista.oneToDto(ControlGastoRepository.findById(id), ControlesGastosDTO.class);
    }

    @Override
    public ControlesGastosDTO create(ControlesGastosDTO controlGasto) {
        ControlesGastos user = MapperUtils.EntityFromDto(controlGasto, ControlesGastos.class);
        user = ControlGastoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ControlesGastosDTO.class);
    }

    @Override
    public Optional<ControlesGastosDTO> update(ControlesGastosDTO controlGasto, Long id) {
        if (ControlGastoRepository.findById(id).isPresent()) {
            ControlesGastos user = MapperUtils.EntityFromDto(controlGasto, ControlesGastos.class);
            user = ControlGastoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ControlesGastosDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<ControlesGastosDTO>> findByEmpresaContratanteContainingIgnoreCase(String empresa) {
        return (Optional<List<ControlesGastosDTO>>)ConversionLista.findList(ControlGastoRepository.findByEmpresaContratanteContainingIgnoreCase(empresa),ControlesGastosDTO.class);
    }

    @Override
    public Optional<ControlesGastosDTO> findByNumeroContrato(String numeroContrato) {
return (Optional<ControlesGastosDTO>) ConversionLista.oneToDto(Optional.ofNullable(ControlGastoRepository.findByNumeroContrato(numeroContrato)), ControlesGastosDTO.class);    }

   

    @Override
    public Optional<List<ControlesGastosDTO>> findByFechaRegistro(Date fecha) {
        return (Optional<List<ControlesGastosDTO>>)ConversionLista.findList(Optional.ofNullable(ControlGastoRepository.findByFechaRegistro(fecha)),ControlesGastosDTO.class);
    }

    @Override
    public Optional<List<ControlesGastosDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return (Optional<List<ControlesGastosDTO>>)ConversionLista.findList(Optional.ofNullable(ControlGastoRepository.findByFechaRegistroBetween(startDate, endDate)),ControlesGastosDTO.class);
    }

    @Override
    public Optional<ControlesGastosDTO> findByDetalleControlGastoId(Long id) {
       return (Optional<ControlesGastosDTO>)ConversionLista.oneToDto(Optional.ofNullable(ControlGastoRepository.findByDetalleControlGastoId(id)),ControlesGastosDTO.class);
    }

}
