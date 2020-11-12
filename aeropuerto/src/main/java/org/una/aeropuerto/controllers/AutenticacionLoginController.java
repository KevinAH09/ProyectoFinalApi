/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.service.IAutenticacionLoginService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/login")
@Api(tags = {"Seguridad"})
public class AutenticacionLoginController {

    @Autowired
    private IAutenticacionLoginService login;

    final String MENSAJE_VERIFICAR_CREDENCIALES = "Debe verificar y proporcionar credenciales correctos para realizar esta acción";
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuariosDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
        }
        try {
            System.out.println("org.una.aeropuerto.controllers.AutenticacionLoginController.login()eqwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse = login.login(authenticationRequest);
            System.out.println(authenticationResponse.getJwt());
            return new ResponseEntity(authenticationResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
