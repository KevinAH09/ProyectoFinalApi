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
import org.una.aeropuerto.dto.VueloDTO;
import org.una.aeropuerto.entities.Vuelo;
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
    public Optional<List<VueloDTO>> findAll() {
        return (Optional<List<VueloDTO>>) ConversionLista.findList((VueloRepository.findAll()), VueloDTO.class);
    }

    @Override
    public Optional<VueloDTO> findById(Long id) {
        return (Optional<VueloDTO>) ConversionLista.oneToDto(VueloRepository.findById(id), VueloDTO.class);
    }

    @Override
    public VueloDTO create(VueloDTO vuelo) {
        Vuelo user = MapperUtils.EntityFromDto(vuelo, Vuelo.class);
        user = VueloRepository.save(user);
        return MapperUtils.DtoFromEntity(user, VueloDTO.class);
    }

    @Override
    public Optional<VueloDTO> update(VueloDTO vuelo, Long id) {
        if (VueloRepository.findById(id).isPresent()) {
            Vuelo user = MapperUtils.EntityFromDto(vuelo, Vuelo.class);
            user = VueloRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, VueloDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<VueloDTO>> findByEstadoContaining(boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<List<VueloDTO>> findByDestino(String destino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<List<VueloDTO>> findByOrigen(String origen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<List<VueloDTO>> findByAvionId(Long avion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<List<VueloDTO>> findBybitacoraVueloId(Long bitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
