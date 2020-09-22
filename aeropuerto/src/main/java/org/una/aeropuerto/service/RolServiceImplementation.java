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
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.repositories.IRolRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository RolRepository;

    @Override
    public Optional<List<RolDTO>> findAll() {
        return (Optional<List<RolDTO>>) ConversionLista.findList((RolRepository.findAll()), RolDTO.class);
    }

    @Override
    public Optional<RolDTO> findById(Long id) {
        return (Optional<RolDTO>) ConversionLista.oneToDto(RolRepository.findById(id), RolDTO.class);
    }

    @Override
    public RolDTO create(RolDTO rol) {
        Rol user = MapperUtils.EntityFromDto(rol, Rol.class);
        user = RolRepository.save(user);
        return MapperUtils.DtoFromEntity(user, RolDTO.class);
    }

    @Override
    public Optional<RolDTO> update(RolDTO rol, Long id) {
        if (RolRepository.findById(id).isPresent()) {
            Rol user = MapperUtils.EntityFromDto(rol, Rol.class);
            user = RolRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, RolDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<RolDTO>> findByEstado(boolean estado) {
        return (Optional<List<RolDTO>>) ConversionLista.findList(Optional.ofNullable(RolRepository.findByEstado(estado)), RolDTO.class);
    }

    @Override
    public Optional<RolDTO> findByCodigo(String codigo) {
        return (Optional<RolDTO>) ConversionLista.oneToDto(Optional.ofNullable(RolRepository.findByCodigo(codigo)), RolDTO.class);
    }

}
