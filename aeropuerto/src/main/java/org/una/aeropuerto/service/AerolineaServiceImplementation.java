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
import org.una.aeropuerto.dto.AerolineaDTO;
import org.una.aeropuerto.entities.Aerolinea;
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
    public Optional<List<AerolineaDTO>> findAll() {
        return (Optional<List<AerolineaDTO>>) ConversionLista.findList((aerolineaRepository.findAll()), AerolineaDTO.class);
    }

    @Override
    public Optional<AerolineaDTO> findById(Long id) {
        return (Optional<AerolineaDTO>) ConversionLista.oneToDto(aerolineaRepository.findById(id), AerolineaDTO.class);
    }

    @Override
    public Optional<List<AerolineaDTO>> findByEstado(boolean estado) {
        return (Optional<List<AerolineaDTO>>) ConversionLista.findList(Optional.ofNullable(aerolineaRepository.findByEstado(estado)), AerolineaDTO.class);

    }

    @Override
    public Optional<List<AerolineaDTO>> findByNombreAerolineaContainingIgnoreCase(String nombreAerolinea) {
        return (Optional<List<AerolineaDTO>>) ConversionLista.findList(aerolineaRepository.findByNombreAerolineaContainingIgnoreCase(nombreAerolinea), AerolineaDTO.class);

    }

    @Override
    public Optional<List<AerolineaDTO>> findByNombreResponsableContainingIgnoreCase(String nombreResponsable) {
        return (Optional<List<AerolineaDTO>>) ConversionLista.findList(aerolineaRepository.findByNombreResponsableContainingIgnoreCase(nombreResponsable), AerolineaDTO.class);
    }

    @Override
    public AerolineaDTO create(AerolineaDTO aerolinea) {
        Aerolinea user = MapperUtils.EntityFromDto(aerolinea, Aerolinea.class);
        user = aerolineaRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AerolineaDTO.class);
    }

    @Override
    public Optional<AerolineaDTO> update(AerolineaDTO aerolinea, Long id) {
        if (aerolineaRepository.findById(id).isPresent()) {
            Aerolinea user = MapperUtils.EntityFromDto(aerolinea, Aerolinea.class);
            user = aerolineaRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AerolineaDTO.class));
        } else {
            return null;
        }
    }

}
