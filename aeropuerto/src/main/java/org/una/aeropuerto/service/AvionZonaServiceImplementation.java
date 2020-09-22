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
import org.una.aeropuerto.dto.AvionZonaDTO;
import org.una.aeropuerto.entities.AvionZona;
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
    public Optional<List<AvionZonaDTO>> findAll() {
        return (Optional<List<AvionZonaDTO>>) ConversionLista.findList((AvionRepository.findAll()), AvionZonaDTO.class);
    }

    @Override
    public Optional<AvionZonaDTO> findById(Long id) {
        return (Optional<AvionZonaDTO>) ConversionLista.oneToDto(AvionRepository.findById(id), AvionZonaDTO.class);
    }

    @Override
    public AvionZonaDTO create(AvionZonaDTO avionZona) {
        AvionZona user = MapperUtils.EntityFromDto(avionZona, AvionZona.class);
        user = AvionRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AvionZonaDTO.class);
    }

    @Override
    public Optional<AvionZonaDTO> update(AvionZonaDTO avionZona, Long id) {
        if (AvionRepository.findById(id).isPresent()) {
            AvionZona user = MapperUtils.EntityFromDto(avionZona, AvionZona.class);
            user = AvionRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AvionZonaDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<AvionZonaDTO>> findByAvionId(Long avion) {
        return (Optional<List<AvionZonaDTO>>) ConversionLista.findList(AvionRepository.findByAvionId(avion),AvionZonaDTO.class);
    }

    @Override
    public Optional<List<AvionZonaDTO>> findByZonaId(Long zona) {
        return (Optional<List<AvionZonaDTO>>) ConversionLista.findList(AvionRepository.findByZonaId(zona),AvionZonaDTO.class);
    }

}
