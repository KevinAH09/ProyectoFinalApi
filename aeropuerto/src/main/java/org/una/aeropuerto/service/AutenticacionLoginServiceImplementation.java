/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.jwt.JwtProvider;
import org.una.aeropuerto.repositories.IUsuarioRepository;

/**
 *
 * @author Bosco
 */
@Service
public class AutenticacionLoginServiceImplementation implements UserDetailsService,IAutenticacionLoginService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getContrasena()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Optional<UsuarioDTO> usuario = usuarioService.findByCedula(authenticationRequest.getCedula());

        if (usuario.isPresent()) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            UsuarioDTO usuarioDto = usuario.get();
            authenticationResponse.setUsuario(usuarioDto);

            return authenticationResponse;
        } else {
            return null;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuarioBuscado = usuarioRepository.findByCedula(username);
        if (usuarioBuscado != null) {
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ADMIN"));
            UserDetails userDetails = new User(usuarioBuscado.getCedula(), usuarioBuscado.getContrasenaEncriptada(), roles);
            return userDetails;
        } else {
            return null;
        }

    }

}