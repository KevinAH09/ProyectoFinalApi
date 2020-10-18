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
import org.una.aeropuerto.dto.AvionesZonasDTO;
import org.una.aeropuerto.entities.AvionesZonas;
import org.una.aeropuerto.repositories.IAvionZonaRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class AvionZonaServiceImplementation implements IAvionZonaService {

    @Autowired
    private IAvionZonaRepository AvionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionesZonasDTO>> findAll() {
        return (Optional<List<AvionesZonasDTO>>) ConversionLista.findList((AvionRepository.findAll()), AvionesZonasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AvionesZonasDTO> findById(Long id) {
        return (Optional<AvionesZonasDTO>) ConversionLista.oneToDto(AvionRepository.findById(id), AvionesZonasDTO.class);
    }

    @Override
    @Transactional
    public AvionesZonasDTO create(AvionesZonasDTO avionZona) {
        AvionesZonas user = MapperUtils.EntityFromDto(avionZona, AvionesZonas.class);
        user = AvionRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AvionesZonasDTO.class);
    }

    @Override
    @Transactional
    public Optional<AvionesZonasDTO> update(AvionesZonasDTO avionZona, Long id) {
        if (AvionRepository.findById(id).isPresent()) {
            AvionesZonas user = MapperUtils.EntityFromDto(avionZona, AvionesZonas.class);
            user = AvionRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AvionesZonasDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionesZonasDTO>> findByAvion(Long avion) {
        return (Optional<List<AvionesZonasDTO>>) ConversionLista.findList(AvionRepository.findByAvion(avion),AvionesZonasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionesZonasDTO>> findByZonaId(Long zona) {
        return (Optional<List<AvionesZonasDTO>>) ConversionLista.findList(AvionRepository.findByZonaId(zona),AvionesZonasDTO.class);
    }

}
