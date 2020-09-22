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
import org.una.aeropuerto.dto.ZonaDTO;
import org.una.aeropuerto.entities.Zona;
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
    public Optional<List<ZonaDTO>> findAll() {
        return (Optional<List<ZonaDTO>>) ConversionLista.findList((ZonaRepository.findAll()), ZonaDTO.class);
    }

    @Override
    public Optional<ZonaDTO> findById(Long id) {
        return (Optional<ZonaDTO>) ConversionLista.oneToDto(ZonaRepository.findById(id), ZonaDTO.class);
    }

    @Override
    public ZonaDTO create(ZonaDTO zona) {
        Zona user = MapperUtils.EntityFromDto(zona, Zona.class);
        user = ZonaRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ZonaDTO.class);
    }

    @Override
    public Optional<ZonaDTO> update(ZonaDTO zona, Long id) {
        if (ZonaRepository.findById(id).isPresent()) {
            Zona user = MapperUtils.EntityFromDto(zona, Zona.class);
            user = ZonaRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ZonaDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<ZonaDTO>> findByEstadoContaining(boolean estado) {
        return (Optional<List<ZonaDTO>>) ConversionLista.findList(Optional.ofNullable(ZonaRepository.findByEstadoContaining(estado)), ZonaDTO.class);
    }

    @Override
    public Optional<List<ZonaDTO>> findByNombreZonaContainingIgnoreCase(String nombreZona) {
        return (Optional<List<ZonaDTO>>) ConversionLista.findList(ZonaRepository.findByNombreZonaContainingIgnoreCase(nombreZona), ZonaDTO.class);
    }

    @Override
    public Optional<List<ZonaDTO>> findByCodigo(String codigo) {
        return (Optional<List<ZonaDTO>>) ConversionLista.findList(ZonaRepository.findByCodigo(codigo), ZonaDTO.class);
    }

}
