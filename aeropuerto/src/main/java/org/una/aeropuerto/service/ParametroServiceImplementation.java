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
import org.una.aeropuerto.dto.ParametrosDTO;
import org.una.aeropuerto.entities.Parametros;
import org.una.aeropuerto.repositories.IParametroRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class ParametroServiceImplementation implements IParametroService {

    @Autowired
    private IParametroRepository ParametroRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findAll() {
        return (Optional<List<ParametrosDTO>>) ConversionLista.findList((ParametroRepository.findAll()), ParametrosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosDTO> findById(Long id) {
        return (Optional<ParametrosDTO>) ConversionLista.oneToDto(ParametroRepository.findById(id), ParametrosDTO.class);
    }

    @Override
    @Transactional
    public ParametrosDTO create(ParametrosDTO parametro) {
        Parametros user = MapperUtils.EntityFromDto(parametro, Parametros.class);
        user = ParametroRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ParametrosDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametrosDTO> update(ParametrosDTO parametro, Long id) {
        if (ParametroRepository.findById(id).isPresent()) {
            Parametros user = MapperUtils.EntityFromDto(parametro, Parametros.class);
            user = ParametroRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ParametrosDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findByEstado(boolean estado) {
        return (Optional<List<ParametrosDTO>>) ConversionLista.findList(Optional.ofNullable(ParametroRepository.findByEstado(estado)), ParametrosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosDTO> findByNombreParametro(String nombreParametro) {
        return (Optional<ParametrosDTO>) ConversionLista.oneToDto(Optional.ofNullable(ParametroRepository.findByNombreParametro(nombreParametro)), ParametrosDTO.class);
    }

}
