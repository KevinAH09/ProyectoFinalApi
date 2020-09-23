/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.RegistroAccionDTO;
import org.una.aeropuerto.service.IRegistroAccionService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/Registro_accion")
@Api(tags = {"Registro de acciones"})
public class RegistroAccionController {
    
    
    @Autowired
    private IRegistroAccionService registroAccionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos registros de acciones", response = RegistroAccionDTO.class, responseContainer = "List", tags = "Registro de acciones")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(registroAccionService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un registro de accion", response = RegistroAccionDTO.class, tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(registroAccionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un registro de accion", response = RegistroAccionDTO.class, tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody RegistroAccionDTO registroAccionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(registroAccionService.create(registroAccionDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un registro de accion", response = RegistroAccionDTO.class, tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RegistroAccionDTO registroAccionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<RegistroAccionDTO> registroAccionUpdated = registroAccionService.update(registroAccionDTO, id);
                if (registroAccionUpdated.isPresent()) {
                    return new ResponseEntity(registroAccionUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/fechaRegistro/{date}")
    @ApiOperation(value = "Obtiene una lista de todos los registros accion por fecha de registro", response = RegistroAccionDTO.class, responseContainer = "List", tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "date") Date fecha) {
        try {
            return new ResponseEntity<>(registroAccionService.findByFechaRegistro(fecha), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fechaRegistroStar/{dateStar}/fechaRegistroEnd/{dateEnd}")
    @ApiOperation(value = "Obtiene una lista de todos los registros de acciones por rango de fechas de registro", response = RegistroAccionDTO.class, responseContainer = "List", tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "dateStar") Date fechaStar,@PathVariable(value = "dateEnd") Date fechaEnd) {
        try {
            return new ResponseEntity<>(registroAccionService.findByFechaRegistroBetween(fechaStar, fechaEnd), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
