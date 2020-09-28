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
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.RegistrosAccionesDTO;
import org.una.aeropuerto.entities.RegistrosAcciones;
import org.una.aeropuerto.repositories.IRegistroAccionRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class RegistroAccionServiceImplementation implements IRegistroAccionService {

    @Autowired
    private IRegistroAccionRepository RegistroAccionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RegistrosAccionesDTO>> findAll() {
        return (Optional<List<RegistrosAccionesDTO>>) ConversionLista.findList(RegistroAccionRepository.findAll(), RegistrosAccionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RegistrosAccionesDTO> findById(Long id) {
        return (Optional<RegistrosAccionesDTO>) ConversionLista.oneToDto(RegistroAccionRepository.findById(id), RegistrosAccionesDTO.class);
    }

    @Override
    @Transactional
    public RegistrosAccionesDTO create(RegistrosAccionesDTO registroAccion) {
        RegistrosAcciones user = MapperUtils.EntityFromDto(registroAccion, RegistrosAcciones.class);
        user = RegistroAccionRepository.save(user);
        return MapperUtils.DtoFromEntity(user, RegistrosAccionesDTO.class);
    }

    @Override
    @Transactional
    public Optional<RegistrosAccionesDTO> update(RegistrosAccionesDTO registroAccion, Long id) {
        if (RegistroAccionRepository.findById(id).isPresent()) {
            RegistrosAcciones user = MapperUtils.EntityFromDto(registroAccion, RegistrosAcciones.class);
            user = RegistroAccionRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, RegistrosAccionesDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RegistrosAccionesDTO>> findByUsuarioId(Long id) {
        return (Optional<List<RegistrosAccionesDTO>>) ConversionLista.findList(RegistroAccionRepository.findByUsuarioId(id), RegistrosAccionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RegistrosAccionesDTO>> findByFechaRegistro(Date fecha) {
        return (Optional<List<RegistrosAccionesDTO>>)ConversionLista.findList(Optional.ofNullable(RegistroAccionRepository.findByFechaRegistro(fecha)),RegistrosAccionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RegistrosAccionesDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return (Optional<List<RegistrosAccionesDTO>>)ConversionLista.findList(Optional.ofNullable(RegistroAccionRepository.findByFechaRegistroBetween(startDate, endDate)),RegistrosAccionesDTO.class);
    }

}
