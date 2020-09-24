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
import org.una.aeropuerto.dto.ParametroDTO;
import org.una.aeropuerto.entities.Parametro;
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
    public Optional<List<ParametroDTO>> findAll() {
        return (Optional<List<ParametroDTO>>) ConversionLista.findList((ParametroRepository.findAll()), ParametroDTO.class);
    }

    @Override
    public Optional<ParametroDTO> findById(Long id) {
        return (Optional<ParametroDTO>) ConversionLista.oneToDto(ParametroRepository.findById(id), ParametroDTO.class);
    }

    @Override
    public ParametroDTO create(ParametroDTO parametro) {
        Parametro user = MapperUtils.EntityFromDto(parametro, Parametro.class);
        user = ParametroRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ParametroDTO.class);
    }

    @Override
    public Optional<ParametroDTO> update(ParametroDTO parametro, Long id) {
if (ParametroRepository.findById(id).isPresent()) {
            Parametro user = MapperUtils.EntityFromDto(parametro, Parametro.class);
            user = ParametroRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ParametroDTO.class));
        } else {
            return null;
        }    }

    @Override
    public Optional<List<ParametroDTO>> findByEstado(boolean estado) {
        return (Optional<List<ParametroDTO>>)ConversionLista.findList(Optional.ofNullable(ParametroRepository.findByEstado(estado)),ParametroDTO.class);
    }

    @Override
    public Optional<List<ParametroDTO>> findByNombreParametro(String nombreParametro) {
        return (Optional<List<ParametroDTO>>) ConversionLista.findList(ParametroRepository.findByNombreParametro(nombreParametro),ParametroDTO.class);
    }

}
