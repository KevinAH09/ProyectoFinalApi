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
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.entities.Roles;
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
    @Transactional(readOnly = true)
    public Optional<List<RolesDTO>> findAll() {
        return (Optional<List<RolesDTO>>) ConversionLista.findList((RolRepository.findAll()), RolesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findById(Long id) {
        return (Optional<RolesDTO>) ConversionLista.oneToDto(RolRepository.findById(id), RolesDTO.class);
    }

    @Override
    @Transactional
    public RolesDTO create(RolesDTO rol) {
        Roles user = MapperUtils.EntityFromDto(rol, Roles.class);
        user = RolRepository.save(user);
        return MapperUtils.DtoFromEntity(user, RolesDTO.class);
    }

    @Override
    @Transactional
    public Optional<RolesDTO> update(RolesDTO rol, Long id) {
        if (RolRepository.findById(id).isPresent()) {
            Roles user = MapperUtils.EntityFromDto(rol, Roles.class);
            user = RolRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, RolesDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolesDTO>> findByEstado(boolean estado) {
        return (Optional<List<RolesDTO>>) ConversionLista.findList(Optional.ofNullable(RolRepository.findByEstado(estado)), RolesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findByCodigo(String codigo) {
        return (Optional<RolesDTO>) ConversionLista.oneToDto(Optional.ofNullable(RolRepository.findByCodigo(codigo)), RolesDTO.class);
    }

}
