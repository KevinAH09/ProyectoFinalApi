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
import org.una.aeropuerto.dto.AerolineasDTO;
import org.una.aeropuerto.entities.Aerolineas;
import org.una.aeropuerto.repositories.IAerolineaRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class AerolineaServiceImplementation implements IAerolineaService {

    @Autowired
    private IAerolineaRepository aerolineaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineasDTO>> findAll() {
        return (Optional<List<AerolineasDTO>>) ConversionLista.findList((aerolineaRepository.findAll()), AerolineasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AerolineasDTO> findById(Long id) {
        return (Optional<AerolineasDTO>) ConversionLista.oneToDto(aerolineaRepository.findById(id), AerolineasDTO.class);
    }

    @Override
    public Optional<List<AerolineasDTO>> findByEstado(boolean estado) {
        return (Optional<List<AerolineasDTO>>) ConversionLista.findList(Optional.ofNullable(aerolineaRepository.findByEstado(estado)), AerolineasDTO.class);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineasDTO>> findByNombreAerolineaContainingIgnoreCase(String nombreAerolinea) {
        return (Optional<List<AerolineasDTO>>) ConversionLista.findList(aerolineaRepository.findByNombreAerolineaContainingIgnoreCase(nombreAerolinea), AerolineasDTO.class);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineasDTO>> findByNombreResponsableContainingIgnoreCase(String nombreResponsable) {
        return (Optional<List<AerolineasDTO>>) ConversionLista.findList(aerolineaRepository.findByNombreResponsableContainingIgnoreCase(nombreResponsable), AerolineasDTO.class);
    }

    @Override
    @Transactional
    public AerolineasDTO create(AerolineasDTO aerolinea) {
        Aerolineas user = MapperUtils.EntityFromDto(aerolinea, Aerolineas.class);
        user = aerolineaRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AerolineasDTO.class);
    }

    @Override
    @Transactional
    public Optional<AerolineasDTO> update(AerolineasDTO aerolinea, Long id) {
        if (aerolineaRepository.findById(id).isPresent()) {
            Aerolineas user = MapperUtils.EntityFromDto(aerolinea, Aerolineas.class);
            user = aerolineaRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AerolineasDTO.class));
        } else {
            return null;
        }
    }

}
