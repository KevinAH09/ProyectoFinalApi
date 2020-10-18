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
import org.una.aeropuerto.dto.AreasTrabajosDTO;
import org.una.aeropuerto.entities.AreasTrabajos;
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
    @Transactional(readOnly = true)
    public Optional<List<AreasTrabajosDTO>> findAll() {
        return (Optional<List<AreasTrabajosDTO>>) ConversionLista.findList((AreaTrabajoRepository.findAll()), AreasTrabajosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreasTrabajosDTO> findById(Long id) {
        return (Optional<AreasTrabajosDTO>) ConversionLista.oneToDto(AreaTrabajoRepository.findById(id), AreasTrabajosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreasTrabajosDTO>> findByEstado(boolean estado) {
        return (Optional<List<AreasTrabajosDTO>>) ConversionLista.findList(AreaTrabajoRepository.findByEstado(estado), AreasTrabajosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreasTrabajosDTO>> findByNombreAreaTrabajoContainingIgnoreCase(String nombreAreaTrabajo) {
        return (Optional<List<AreasTrabajosDTO>>) ConversionLista.findList(AreaTrabajoRepository.findByNombreAreaTrabajoContainingIgnoreCase(nombreAreaTrabajo), AreasTrabajosDTO.class);
    }

    @Override
    @Transactional
    public AreasTrabajosDTO create(AreasTrabajosDTO areaTrabajo) {
        AreasTrabajos user = MapperUtils.EntityFromDto(areaTrabajo, AreasTrabajos.class);
        user = AreaTrabajoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AreasTrabajosDTO.class);
    }

    @Override
    @Transactional
    public Optional<AreasTrabajosDTO> update(AreasTrabajosDTO areaTrabajo, Long id) {
        if (AreaTrabajoRepository.findById(id).isPresent()) {
            AreasTrabajos user = MapperUtils.EntityFromDto(areaTrabajo, AreasTrabajos.class);
            user = AreaTrabajoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AreasTrabajosDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreasTrabajosDTO> findByNombreAreaTrabajo(String nombreAreaTrabajo) {
        return (Optional<AreasTrabajosDTO>) ConversionLista.oneToDto(Optional.ofNullable(AreaTrabajoRepository.findByNombreAreaTrabajo(nombreAreaTrabajo)), AreasTrabajosDTO.class);
    }

}
