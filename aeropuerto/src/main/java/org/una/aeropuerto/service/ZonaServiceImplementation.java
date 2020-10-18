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
import org.una.aeropuerto.dto.ZonasDTO;
import org.una.aeropuerto.entities.Zonas;
import org.una.aeropuerto.repositories.IZonaRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class ZonaServiceImplementation implements IZonaService {

    @Autowired
    private IZonaRepository ZonaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ZonasDTO>> findAll() {
        return (Optional<List<ZonasDTO>>) ConversionLista.findList((ZonaRepository.findAll()), ZonasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ZonasDTO> findById(Long id) {
        return (Optional<ZonasDTO>) ConversionLista.oneToDto(ZonaRepository.findById(id), ZonasDTO.class);
    }

    @Override
    @Transactional
    public ZonasDTO create(ZonasDTO zona) {
        Zonas user = MapperUtils.EntityFromDto(zona, Zonas.class);
        user = ZonaRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ZonasDTO.class);
    }

    @Override
    @Transactional
    public Optional<ZonasDTO> update(ZonasDTO zona, Long id) {
        if (ZonaRepository.findById(id).isPresent()) {
            Zonas user = MapperUtils.EntityFromDto(zona, Zonas.class);
            user = ZonaRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ZonasDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ZonasDTO>> findByEstado(boolean estado) {
        return (Optional<List<ZonasDTO>>) ConversionLista.findList(Optional.ofNullable(ZonaRepository.findByEstado(estado)), ZonasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ZonasDTO>> findByNombreZonaContainingIgnoreCase(String nombreZona) {
        return (Optional<List<ZonasDTO>>) ConversionLista.findList(ZonaRepository.findByNombreZonaContainingIgnoreCase(nombreZona), ZonasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ZonasDTO>> findByCodigo(String codigo) {
        return (Optional<List<ZonasDTO>>) ConversionLista.findList(ZonaRepository.findByCodigo(codigo), ZonasDTO.class);
    }

}
