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
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.repositories.IAreaTrabajoRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {

    @Autowired
    private IAreaTrabajoRepository AreaTrabajoRepository;

    @Override
    public Optional<List<AreaTrabajoDTO>> findAll() {
        return (Optional<List<AreaTrabajoDTO>>) ConversionLista.findList((AreaTrabajoRepository.findAll()), AreaTrabajoDTO.class);
    }

    @Override
    public Optional<AreaTrabajoDTO> findById(Long id) {
        return (Optional<AreaTrabajoDTO>) ConversionLista.oneToDto(AreaTrabajoRepository.findById(id), AreaTrabajoDTO.class);
    }

    @Override
    public Optional<List<AreaTrabajoDTO>> findByEstado(boolean estado) {
        return (Optional<List<AreaTrabajoDTO>>) ConversionLista.findList(Optional.ofNullable(AreaTrabajoRepository.findByEstado(estado)), AreaTrabajoDTO.class);
    }

    @Override
    public Optional<List<AreaTrabajoDTO>> findByNombreAreaTrabajoContainingIgnoreCase(String nombreAreaTrabajo) {
        return (Optional<List<AreaTrabajoDTO>>) ConversionLista.findList(AreaTrabajoRepository.findByNombreAreaTrabajoContainingIgnoreCase(nombreAreaTrabajo), AreaTrabajoDTO.class);
    }

    @Override
    public AreaTrabajoDTO create(AreaTrabajoDTO areaTrabajo) {
        AreaTrabajo user = MapperUtils.EntityFromDto(areaTrabajo, AreaTrabajo.class);
        user = AreaTrabajoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AreaTrabajoDTO.class);
    }

    @Override
    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO areaTrabajo, Long id) {
        if (AreaTrabajoRepository.findById(id).isPresent()) {
            AreaTrabajo user = MapperUtils.EntityFromDto(areaTrabajo, AreaTrabajo.class);
            user = AreaTrabajoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AreaTrabajoDTO.class));
        } else {
            return null;
        }
    }

}
