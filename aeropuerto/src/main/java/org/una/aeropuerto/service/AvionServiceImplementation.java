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
import org.una.aeropuerto.dto.AvionDTO;
import org.una.aeropuerto.entities.Avion;
import org.una.aeropuerto.repositories.IAvionRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class AvionServiceImplementation implements IAvionService {

    @Autowired
    private IAvionRepository AvionRepository;

    @Override
    public Optional<List<AvionDTO>> findAll() {
        return (Optional<List<AvionDTO>>) ConversionLista.findList((AvionRepository.findAll()), AvionDTO.class);
    }

    @Override
    public Optional<AvionDTO> findById(Long id) {
        return (Optional<AvionDTO>) ConversionLista.oneToDto(AvionRepository.findById(id), AvionDTO.class);
    }

    @Override
    public AvionDTO create(AvionDTO avion) {
        Avion user = MapperUtils.EntityFromDto(avion, Avion.class);
        user = AvionRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AvionDTO.class);
    }

    @Override
    public Optional<AvionDTO> update(AvionDTO avion, Long id) {
        if (AvionRepository.findById(id).isPresent()) {
            Avion user = MapperUtils.EntityFromDto(avion, Avion.class);
            user = AvionRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AvionDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<AvionDTO>> findByMatriculaContainingIgnoreCase(String matricula) {
        return (Optional<List<AvionDTO>>) ConversionLista.findList(AvionRepository.findByMatriculaContainingIgnoreCase(matricula), AvionDTO.class);
    }

    @Override
    public Optional<List<AvionDTO>> findBytipoAvionContainingIgnoreCase(String tipoAvion) {
        return (Optional<List<AvionDTO>>) ConversionLista.findList(AvionRepository.findBytipoAvionContainingIgnoreCase(tipoAvion), AvionDTO.class);
    }

    @Override
    public Optional<List<AvionDTO>> findByAerolineaId(Long id) {
        return (Optional<List<AvionDTO>>) ConversionLista.findList(AvionRepository.findByAerolineaId(id),AvionDTO.class);
    }

}
