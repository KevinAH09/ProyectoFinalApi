/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.entities.Usuarios;
import org.una.aeropuerto.repositories.IUsuarioRepository;
import org.una.aeropuerto.utils.ConversionLista;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class UsuarioServiceImplementation implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuariosDTO>> findAll() {
        return (Optional<List<UsuariosDTO>>) ConversionLista.findList((usuarioRepository.findAll()), UsuariosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuariosDTO> findById(Long id) {
        return (Optional<UsuariosDTO>) ConversionLista.oneToDto(usuarioRepository.findById(id), UsuariosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuariosDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return (Optional<List<UsuariosDTO>>) ConversionLista.findList(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto), UsuariosDTO.class);
    }

    @Override
    @Transactional
    public UsuariosDTO create(UsuariosDTO usuario) {
        encriptarPassword(usuario);
        Usuarios user = MapperUtils.EntityFromDto(usuario, Usuarios.class);
        user = usuarioRepository.save(user);
        return MapperUtils.DtoFromEntity(user, UsuariosDTO.class);
    }

    @Override
    @Transactional
    public Optional<UsuariosDTO> update(UsuariosDTO usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usuarioRepository.save(MapperUtils.EntityFromDto(usuario, Usuarios.class)), UsuariosDTO.class));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuariosDTO>> findByEstado(boolean estado) {
        return (Optional<List<UsuariosDTO>>) ConversionLista.findList(Optional.ofNullable(usuarioRepository.findByEstado(estado)), UsuariosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuariosDTO> findByCedula(String cedula) {
        return (Optional<UsuariosDTO>) ConversionLista.oneToDto(Optional.ofNullable(usuarioRepository.findByCedula(cedula)), UsuariosDTO.class);
    }

    private UsuariosDTO encriptarPassword(UsuariosDTO usuario) {
        String password = usuario.getContrasenaEncriptada();
        if (!password.isBlank()) {
            usuario.setContrasenaEncriptada(bCryptPasswordEncoder.encode(password));
        }
        return usuario;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuariosDTO> findByCedulaAndContrasenaEncriptado(String cedula, String password) {
        return (Optional<UsuariosDTO>) ConversionLista.oneToDto(Optional.ofNullable(usuarioRepository.findByCedulaAndContrasenaEncriptada(cedula, bCryptPasswordEncoder.encode(password))), UsuariosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuariosDTO>> findByRolId(Long id) {
        return (Optional<List<UsuariosDTO>>) ConversionLista.findList(usuarioRepository.findByRolId(id), UsuariosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuariosDTO>> findByAreaTrabajoId(Long id) {
        return (Optional<List<UsuariosDTO>>) ConversionLista.findList(usuarioRepository.findByAreaTrabajoId(id), UsuariosDTO.class);
    }

    @Override
    public Optional<UsuariosDTO> updateContrasena(UsuariosDTO usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            UsuariosDTO usuCambioContrasena = new UsuariosDTO();
            usuCambioContrasena = encriptarPassword(usuario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usuarioRepository.save(MapperUtils.EntityFromDto(usuCambioContrasena, Usuarios.class)), UsuariosDTO.class));
        } else {
            return null;
        }
    }

}
