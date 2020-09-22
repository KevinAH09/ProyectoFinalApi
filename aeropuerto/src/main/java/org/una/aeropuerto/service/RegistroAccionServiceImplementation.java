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
import org.una.aeropuerto.dto.RegistroAccionDTO;
import org.una.aeropuerto.entities.RegistroAccion;
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
    public Optional<List<RegistroAccionDTO>> findAll() {
        return (Optional<List<RegistroAccionDTO>>) ConversionLista.findList((RegistroAccionRepository.findAll()), RegistroAccionDTO.class);
    }

    @Override
    public Optional<RegistroAccionDTO> findById(Long id) {
        return (Optional<RegistroAccionDTO>) ConversionLista.oneToDto(RegistroAccionRepository.findById(id), RegistroAccionDTO.class);
    }

    @Override
    public RegistroAccionDTO create(RegistroAccionDTO registroAccion) {
        RegistroAccion user = MapperUtils.EntityFromDto(registroAccion, RegistroAccion.class);
        user = RegistroAccionRepository.save(user);
        return MapperUtils.DtoFromEntity(user, RegistroAccionDTO.class);
    }

    @Override
    public Optional<RegistroAccionDTO> update(RegistroAccionDTO registroAccion, Long id) {
        if (RegistroAccionRepository.findById(id).isPresent()) {
            RegistroAccion user = MapperUtils.EntityFromDto(registroAccion, RegistroAccion.class);
            user = RegistroAccionRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, RegistroAccionDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<RegistroAccionDTO>> findByUsuarioId(Long id) {
        return (Optional<List<RegistroAccionDTO>>) ConversionLista.findList(RegistroAccionRepository.findByUsuarioId(id), RegistroAccionDTO.class);
    }

    @Override
    public Optional<List<RegistroAccionDTO>> findByFechaRegistro(Date fecha) {
        return (Optional<List<RegistroAccionDTO>>)ConversionLista.findList(Optional.ofNullable(RegistroAccionRepository.findByFechaRegistro(fecha)),RegistroAccionDTO.class);
    }

    @Override
    public Optional<List<RegistroAccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return (Optional<List<RegistroAccionDTO>>)ConversionLista.findList(Optional.ofNullable(RegistroAccionRepository.findByFechaRegistroBetween(startDate, endDate)),RegistroAccionDTO.class);
    }

}
