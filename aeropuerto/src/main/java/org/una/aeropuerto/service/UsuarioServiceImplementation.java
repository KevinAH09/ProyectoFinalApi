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
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Usuario;
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
    public Optional<List<UsuarioDTO>> findAll() {
        return (Optional<List<UsuarioDTO>>) ConversionLista.findList((usuarioRepository.findAll()),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return (Optional<UsuarioDTO>)ConversionLista.oneToDto(usuarioRepository.findById(id),UsuarioDTO.class);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return (Optional<List<UsuarioDTO>>)ConversionLista.findList(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto),UsuarioDTO.class);
    }

    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuario) {
        encriptarPassword(usuario);
        Usuario user = MapperUtils.EntityFromDto(usuario, Usuario.class);
        user = usuarioRepository.save(user);
        return MapperUtils.DtoFromEntity(user, UsuarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            Usuario user = MapperUtils.EntityFromDto(usuario, Usuario.class);
            user = usuarioRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, UsuarioDTO.class));
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

//    @Override
//    @Transactional(readOnly = true)
//    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
//   
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//
//        Optional<Usuario> usuario = findByCedula(authenticationRequest.getCedula());
//
//        if (usuario.isPresent()) {
//            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
//            authenticationResponse.setUsuario(usuarioDto);
//            List<PermisoOtorgadoDTO> permisosOtorgadosDto = MapperUtils.DtoListFromEntityList(usuario.get().getPermisoOtorgado(), PermisoOtorgadoDTO.class);
//            authenticationResponse.setPermisos(permisosOtorgadosDto);
//
//            return authenticationResponse;
//        } else {
//            return null;
//        }
//    }
    

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByEstado(boolean estado) {
        return (Optional<List<UsuarioDTO>>)ConversionLista.findList(Optional.ofNullable(usuarioRepository.findByEstado(estado)),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCedula(String cedula) {
        return (Optional<UsuarioDTO>)ConversionLista.oneToDto(Optional.ofNullable(usuarioRepository.findByCedula(cedula)),UsuarioDTO.class);
    }

    private UsuarioDTO encriptarPassword(UsuarioDTO usuario) {
        String password = usuario.getContrasenaEncriptada();
        if (!password.isBlank()) {
            usuario.setContrasenaEncriptada(bCryptPasswordEncoder.encode(password));
        }
        return usuario;
    }

    @Override
    public Optional<UsuarioDTO> findByCedulaAndContrasenaEncriptado(String cedula, String password) {
        return (Optional<UsuarioDTO>)ConversionLista.oneToDto(Optional.ofNullable(usuarioRepository.findByCedulaAndContrasenaEncriptada(cedula, bCryptPasswordEncoder.encode(password))),UsuarioDTO.class);
    }

    @Override
    public Optional<List<UsuarioDTO>> findByRolId(Long id) {
        return (Optional<List<UsuarioDTO>>) ConversionLista.findList(usuarioRepository.findByRolId(id),UsuarioDTO.class);
    }

    @Override
    public Optional<List<UsuarioDTO>> findByAreaTrabajoId(Long id) {
        return (Optional<List<UsuarioDTO>>) ConversionLista.findList(usuarioRepository.findByAreaTrabajoId(id),UsuarioDTO.class);
    }

}
