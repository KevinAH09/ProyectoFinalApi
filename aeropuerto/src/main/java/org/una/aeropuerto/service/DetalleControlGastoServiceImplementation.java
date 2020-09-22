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
import org.una.aeropuerto.dto.DetalleControlGastoDTO;
import org.una.aeropuerto.entities.DetalleControlGasto;
import org.una.aeropuerto.repositories.IDetalleControlGastoRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class DetalleControlGastoServiceImplementation implements IDetalleControlGastoService {

    @Autowired
    private IDetalleControlGastoRepository DetalleControlGastoRepository;

    @Override
    public Optional<List<DetalleControlGastoDTO>> findAll() {
        return (Optional<List<DetalleControlGastoDTO>>) ConversionLista.findList((DetalleControlGastoRepository.findAll()), DetalleControlGastoDTO.class);
    }

    @Override
    public Optional<DetalleControlGastoDTO> findById(Long id) {
        return (Optional<DetalleControlGastoDTO>) ConversionLista.oneToDto(DetalleControlGastoRepository.findById(id), DetalleControlGastoDTO.class);
    }

    @Override
    public DetalleControlGastoDTO create(DetalleControlGastoDTO controlGastoDetalle) {
        DetalleControlGasto user = MapperUtils.EntityFromDto(controlGastoDetalle, DetalleControlGasto.class);
        user = DetalleControlGastoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, DetalleControlGastoDTO.class);
    }

    @Override
    public Optional<DetalleControlGastoDTO> update(DetalleControlGastoDTO controlGastoDetalle, Long id) {
        if (DetalleControlGastoRepository.findById(id).isPresent()) {
            DetalleControlGasto user = MapperUtils.EntityFromDto(controlGastoDetalle, DetalleControlGasto.class);
            user = DetalleControlGastoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, DetalleControlGastoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<DetalleControlGastoDTO> findByControlGastoId(Long cedula) {
        return (Optional<DetalleControlGastoDTO>) ConversionLista.oneToDto(Optional.ofNullable(DetalleControlGastoRepository.findByControlGastoId(cedula)), DetalleControlGastoDTO.class);
    }

    @Override
    public Optional<List<DetalleControlGastoDTO>> findByTipoServicio(String tipoServicio) {
        return (Optional<List<DetalleControlGastoDTO>>) ConversionLista.findList(DetalleControlGastoRepository.findByTipoServicio(tipoServicio), DetalleControlGastoDTO.class);
    }
    
    @Override
        public Optional<List<DetalleControlGastoDTO>> findByDuracion(Long duracion) {
        return (Optional<List<DetalleControlGastoDTO>>) ConversionLista.findList(DetalleControlGastoRepository.findByDuracion(duracion), DetalleControlGastoDTO.class);
    }
}




