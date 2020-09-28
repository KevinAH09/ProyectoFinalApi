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
import org.una.aeropuerto.dto.VuelosDTO;
import org.una.aeropuerto.entities.Vuelos;
import org.una.aeropuerto.repositories.IVueloRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class VueloServiceImplementation implements IVueloService {

    @Autowired
    private IVueloRepository VueloRepository;

    @Override
    public Optional<List<VuelosDTO>> findAll() {
        return (Optional<List<VuelosDTO>>) ConversionLista.findList((VueloRepository.findAll()), VuelosDTO.class);
    }

    @Override
    public Optional<VuelosDTO> findById(Long id) {
        return (Optional<VuelosDTO>) ConversionLista.oneToDto(VueloRepository.findById(id), VuelosDTO.class);
    }

    @Override
    public VuelosDTO create(VuelosDTO vuelo) {
        Vuelos user = MapperUtils.EntityFromDto(vuelo, Vuelos.class);
        user = VueloRepository.save(user);
        return MapperUtils.DtoFromEntity(user, VuelosDTO.class);
    }

    @Override
    public Optional<VuelosDTO> update(VuelosDTO vuelo, Long id) {
        if (VueloRepository.findById(id).isPresent()) {
            Vuelos user = MapperUtils.EntityFromDto(vuelo, Vuelos.class);
            user = VueloRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, VuelosDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<VuelosDTO>> findByEstado(boolean estado) {
        return (Optional<List<VuelosDTO>>) ConversionLista.findList(Optional.ofNullable(VueloRepository.findByEstado(estado)), VuelosDTO.class);
    }

    @Override
    public Optional<List<VuelosDTO>> findByDestino(String destino) {
        return (Optional<List<VuelosDTO>>) ConversionLista.findList(VueloRepository.findByDestino(destino), VuelosDTO.class);
    }

    @Override
    public Optional<List<VuelosDTO>> findByOrigen(String origen) {
        return (Optional<List<VuelosDTO>>) ConversionLista.findList(VueloRepository.findByOrigen(origen), VuelosDTO.class);
    }

    @Override
    public Optional<List<VuelosDTO>> findByAvionId(Long avion) {
        return (Optional<List<VuelosDTO>>) ConversionLista.findList(VueloRepository.findByAvionId(avion),VuelosDTO.class);
    }

    @Override
    public Optional<List<VuelosDTO>> findBybitacoraVueloId(Long bitacora) {
        return (Optional<List<VuelosDTO>>) ConversionLista.findList(VueloRepository.findBybitacoraVueloId(bitacora),VuelosDTO.class);
    }

}
