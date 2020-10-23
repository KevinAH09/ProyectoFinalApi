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
import org.una.aeropuerto.dto.AvionesDTO;
import org.una.aeropuerto.entities.Aviones;
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
    @Transactional(readOnly = true)
    public Optional<List<AvionesDTO>> findAll() {
        return (Optional<List<AvionesDTO>>) ConversionLista.findList((AvionRepository.findAll()), AvionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AvionesDTO> findById(Long id) {
        return (Optional<AvionesDTO>) ConversionLista.oneToDto(AvionRepository.findById(id), AvionesDTO.class);
    }

    @Override
    @Transactional
    public AvionesDTO create(AvionesDTO avion) {
        Aviones user = MapperUtils.EntityFromDto(avion, Aviones.class);
        user = AvionRepository.save(user);
        return MapperUtils.DtoFromEntity(user, AvionesDTO.class);
    }

    @Override
    @Transactional
    public Optional<AvionesDTO> update(AvionesDTO avion, Long id) {
        if (AvionRepository.findById(id).isPresent()) {
            Aviones user = MapperUtils.EntityFromDto(avion, Aviones.class);
            user = AvionRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, AvionesDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionesDTO>> findByMatriculaContainingIgnoreCase(String matricula) {
        return (Optional<List<AvionesDTO>>) ConversionLista.findList(AvionRepository.findByMatriculaContainingIgnoreCase(matricula), AvionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionesDTO>> findBytipoAvionContainingIgnoreCase(String tipoAvion) {
        return (Optional<List<AvionesDTO>>) ConversionLista.findList(AvionRepository.findBytipoAvionContainingIgnoreCase(tipoAvion), AvionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionesDTO>> findByAerolineaId(Long id) {
        return (Optional<List<AvionesDTO>>) ConversionLista.findList(AvionRepository.findByAerolineaId(id), AvionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionesDTO>> findByEstado(boolean estado) {
        return (Optional<List<AvionesDTO>>) ConversionLista.findList(Optional.ofNullable(AvionRepository.findByEstado(estado)), AvionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AvionesDTO> findByMatricula(String matricula) {
        return (Optional<AvionesDTO>) ConversionLista.oneToDto(Optional.ofNullable(AvionRepository.findByMatricula(matricula)), AvionesDTO.class);
    }

//    @Override
//    public Optional<List<AvionesDTO>> findByAerolineaIdMatricula(Long id, String Matricula) {
//        return (Optional<List<AvionesDTO>>) ConversionLista.findList(Optional.ofNullable(AvionRepository.findByAerolineaIdMatricula(id,Matricula)), AvionesDTO.class);
//    }

}
