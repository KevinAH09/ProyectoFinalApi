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
import org.una.aeropuerto.dto.BitacoraVueloDTO;
import org.una.aeropuerto.entities.BitacoraVuelo;
import org.una.aeropuerto.repositories.IBitacoraVueloRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class BitacoraVueloServiceImplementation implements IBitacoraVueloService {

    @Autowired
    private IBitacoraVueloRepository BitacoraVueloRepository;

    @Override
    public Optional<List<BitacoraVueloDTO>> findAll() {
        return (Optional<List<BitacoraVueloDTO>>) ConversionLista.findList((BitacoraVueloRepository.findAll()), BitacoraVueloDTO.class);
    }

    @Override
    public Optional<BitacoraVueloDTO> findById(Long id) {
        return (Optional<BitacoraVueloDTO>) ConversionLista.oneToDto(BitacoraVueloRepository.findById(id), BitacoraVueloDTO.class);
    }

    @Override
    public Optional<List<BitacoraVueloDTO>> findByTipoBitacoraContainingIgnoreCase(String tipoBitacora) {
        return (Optional<List<BitacoraVueloDTO>>) ConversionLista.findList(BitacoraVueloRepository.findByTipoBitacoraContainingIgnoreCase(tipoBitacora), BitacoraVueloDTO.class);
    }

    @Override
    public BitacoraVueloDTO create(BitacoraVueloDTO bitacora) {
        BitacoraVuelo user = MapperUtils.EntityFromDto(bitacora, BitacoraVuelo.class);
        user = BitacoraVueloRepository.save(user);
        return MapperUtils.DtoFromEntity(user, BitacoraVueloDTO.class);
    }

    @Override
    public Optional<BitacoraVueloDTO> update(BitacoraVueloDTO bitacora, Long id) {
        if (BitacoraVueloRepository.findById(id).isPresent()) {
            BitacoraVuelo user = MapperUtils.EntityFromDto(bitacora, BitacoraVuelo.class);
            user = BitacoraVueloRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, BitacoraVueloDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<BitacoraVueloDTO>> findByCargaPasajero(boolean cargaPasajero) {
        return (Optional<List<BitacoraVueloDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByCargaPasajero(cargaPasajero)),BitacoraVueloDTO.class);
    }

    @Override
    public Optional<List<BitacoraVueloDTO>> findByCargaCombustible(boolean cargaCombustible) {
        return (Optional<List<BitacoraVueloDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByCargaCombustible(cargaCombustible)),BitacoraVueloDTO.class);
    }

    @Override
    public Optional<List<BitacoraVueloDTO>> findByZonaDescarga(boolean zonaDescarga) {
        return (Optional<List<BitacoraVueloDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByZonaDescarga(zonaDescarga)),BitacoraVueloDTO.class);
    }

    @Override
    public Optional<List<BitacoraVueloDTO>> findByAutorizacionTorreControl(boolean autorizacionTorreControl) {
        return (Optional<List<BitacoraVueloDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByAutorizacionTorreControl(autorizacionTorreControl)),BitacoraVueloDTO.class);
    }

}
