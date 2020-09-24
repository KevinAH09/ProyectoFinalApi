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
import org.una.aeropuerto.dto.AvionDTO;
import org.una.aeropuerto.service.IAvionService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/aviones")
@Api(tags = {"Aviones"})
public class AvionController {
    
    @Autowired
    private IAvionService AvionService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de aviones por id", response = AvionDTO.class, tags = "Aviones")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los aviones", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(AvionService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    //@PreAuthorize("hasAuthority('ARCHIVO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody AvionDTO AvionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(AvionService.create(AvionDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AvionDTO AvionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AvionDTO> AvionUpdated = AvionService.update(AvionDTO, id);
                if (AvionUpdated.isPresent()) {
                    return new ResponseEntity(AvionUpdated, HttpStatus.OK);
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
    
    @GetMapping("/matricula/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los aviones por matricula", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    //@PreAuthorize("hasAuthority('USUARIO_CONSULTAR','USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByMatriculaContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(AvionService.findByMatriculaContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/tipo_avion/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los aviones por tipo de avion", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    //@PreAuthorize("hasAuthority('USUARIO_CONSULTAR','USUARIO_CONSULTAR')")
    public ResponseEntity<?> findBytipoAvionContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(AvionService.findBytipoAvionContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/aerolinea/{id}")
    @ApiOperation(value = "Obtiene una lista de aviones por Id de la aerolinea", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByAerolineaId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionService.findByAerolineaId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}