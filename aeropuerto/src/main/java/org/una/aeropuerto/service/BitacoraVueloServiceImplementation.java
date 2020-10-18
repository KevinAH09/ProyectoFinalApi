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
import org.una.aeropuerto.dto.BitacorasVuelosDTO;
import org.una.aeropuerto.entities.BitacorasVuelos;
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
    @Transactional(readOnly = true)
    public Optional<List<BitacorasVuelosDTO>> findAll() {
        return (Optional<List<BitacorasVuelosDTO>>) ConversionLista.findList((BitacoraVueloRepository.findAll()), BitacorasVuelosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BitacorasVuelosDTO> findById(Long id) {
        return (Optional<BitacorasVuelosDTO>) ConversionLista.oneToDto(BitacoraVueloRepository.findById(id), BitacorasVuelosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacorasVuelosDTO>> findByTipoBitacoraContainingIgnoreCase(String tipoBitacora) {
        return (Optional<List<BitacorasVuelosDTO>>) ConversionLista.findList(BitacoraVueloRepository.findByTipoBitacoraContainingIgnoreCase(tipoBitacora), BitacorasVuelosDTO.class);
    }

    @Override
    @Transactional
    public BitacorasVuelosDTO create(BitacorasVuelosDTO bitacora) {
        BitacorasVuelos user = MapperUtils.EntityFromDto(bitacora, BitacorasVuelos.class);
        user = BitacoraVueloRepository.save(user);
        return MapperUtils.DtoFromEntity(user, BitacorasVuelosDTO.class);
    }

    @Override
    @Transactional
    public Optional<BitacorasVuelosDTO> update(BitacorasVuelosDTO bitacora, Long id) {
        if (BitacoraVueloRepository.findById(id).isPresent()) {
            BitacorasVuelos user = MapperUtils.EntityFromDto(bitacora, BitacorasVuelos.class);
            user = BitacoraVueloRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, BitacorasVuelosDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacorasVuelosDTO>> findByCargaPasajero(boolean cargaPasajero) {
        return (Optional<List<BitacorasVuelosDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByCargaPasajero(cargaPasajero)),BitacorasVuelosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacorasVuelosDTO>> findByCargaCombustible(boolean cargaCombustible) {
        return (Optional<List<BitacorasVuelosDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByCargaCombustible(cargaCombustible)),BitacorasVuelosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacorasVuelosDTO>> findByZonaDescarga(boolean zonaDescarga) {
        return (Optional<List<BitacorasVuelosDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByZonaDescarga(zonaDescarga)),BitacorasVuelosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacorasVuelosDTO>> findByAutorizacionTorreControl(boolean autorizacionTorreControl) {
        return (Optional<List<BitacorasVuelosDTO>>)ConversionLista.findList(Optional.ofNullable(BitacoraVueloRepository.findByAutorizacionTorreControl(autorizacionTorreControl)),BitacorasVuelosDTO.class);
    }

}
