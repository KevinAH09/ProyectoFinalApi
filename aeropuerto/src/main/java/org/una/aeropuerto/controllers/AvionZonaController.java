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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.AvionZonaDTO;
import org.una.aeropuerto.service.IAvionZonaService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/avion_zona")
@Api(tags = {"Zonas y Aviones"})
public class AvionZonaController {
    
    @Autowired
    private IAvionZonaService AvionZonaService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de las zonas de los aviones por id", response = AvionZonaDTO.class, tags = "Zonas y Aviones")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionZonaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las zonas de los aviones", response = AvionZonaDTO.class, responseContainer = "List", tags = "Zonas y Aviones")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(AvionZonaService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    //@PreAuthorize("hasAuthority('ARCHIVO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody AvionZonaDTO AvionZonaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(AvionZonaService.create(AvionZonaDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    //@PreAuthorize("hasAuthority('ARCHIVO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AvionZonaDTO AvionZonaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AvionZonaDTO> AvionZonaUpdated = AvionZonaService.update(AvionZonaDTO, id);
                if (AvionZonaUpdated.isPresent()) {
                    return new ResponseEntity(AvionZonaUpdated, HttpStatus.OK);
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
    
    @GetMapping("/zona/{id}")
    @ApiOperation(value = "Obtiene una lista de aviones por Id de la zona", response = AvionZonaDTO.class, responseContainer = "List", tags = "Zonas y Aviones")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByZonaId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionZonaService.findByZonaId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/avion/{id}")
    @ApiOperation(value = "Obtiene una lista de zonas por Id del avion", response = AvionZonaDTO.class, responseContainer = "List", tags = "Zonas y Aviones")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByAvionId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionZonaService.findByAvionId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
