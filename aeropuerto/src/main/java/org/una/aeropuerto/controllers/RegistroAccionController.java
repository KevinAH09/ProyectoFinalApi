/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.una.aeropuerto.dto.RegistrosAccionesDTO;
import org.una.aeropuerto.service.IRegistroAccionService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/registroAccion")
@Api(tags = {"Registro de acciones"})
public class RegistroAccionController {

    @Autowired
    private IRegistroAccionService registroAccionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos registros de acciones", response = RegistrosAccionesDTO.class, responseContainer = "List", tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(registroAccionService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un registro de accion", response = RegistrosAccionesDTO.class, tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(registroAccionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un registro de accion", response = RegistrosAccionesDTO.class, tags = "Registro de acciones")
    public ResponseEntity<?> create(@Valid @RequestBody RegistrosAccionesDTO registrosAccionesDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(registroAccionService.create(registrosAccionesDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fechaRegistro/{date}")
    @ApiOperation(value = "Obtiene una lista de todos los registros accion por fecha de registro", response = RegistrosAccionesDTO.class, responseContainer = "List", tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "date") String strfecha) throws ParseException {
        Date fechaIni = new SimpleDateFormat("yyyy-MM-dd").parse(strfecha);
        System.out.println(fechaIni);
        Date fechaFin = new Date(fechaIni.getTime() + 86309 * 1000);
        try {
            return new ResponseEntity<>(registroAccionService.findByFechaRegistroBetween(fechaIni, fechaFin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarioId/{id}")
    @ApiOperation(value = "Obtiene una lista de todos los registros accion por usuario", response = RegistrosAccionesDTO.class, responseContainer = "List", tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(registroAccionService.findByUsuarioId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fechaRegistroStar/{dateStar}/fechaRegistroEnd/{dateEnd}")
    @ApiOperation(value = "Obtiene una lista de todos los registros de acciones por rango de fechas de registro", response = RegistrosAccionesDTO.class, responseContainer = "List", tags = "Registro de acciones")
    @PreAuthorize("hasRole('ROLE_AUDITOR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "dateStar") Date fechaStar, @PathVariable(value = "dateEnd") Date fechaEnd) {
        try {
            return new ResponseEntity<>(registroAccionService.findByFechaRegistroBetween(fechaStar, fechaEnd), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
