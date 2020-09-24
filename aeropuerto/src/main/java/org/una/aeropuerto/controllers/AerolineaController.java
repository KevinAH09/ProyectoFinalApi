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
import org.una.aeropuerto.dto.AerolineaDTO;
import org.una.aeropuerto.service.IAerolineaService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/aerolinea")
@Api(tags = {"Aerolineas"})
public class AerolineaController {

    @Autowired
    private IAerolineaService AerolineaService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de Aerolineas por id", response = AerolineaDTO.class, tags = "Aerolineas")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AerolineaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(AerolineaService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    //@PreAuthorize("hasAuthority('ARCHIVO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody AerolineaDTO AerolineaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(AerolineaService.create(AerolineaDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AerolineaDTO AerolineaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AerolineaDTO> AerolineaUpdated = AerolineaService.update(AerolineaDTO, id);
                if (AerolineaUpdated.isPresent()) {
                    return new ResponseEntity(AerolineaUpdated, HttpStatus.OK);
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

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas por estado", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    //@PreAuthorize("hasAuthority('USUARIO_INACTIVAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(AerolineaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre_aerolinea/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas por nombre de la aerolinea", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    //@PreAuthorize("hasAuthority('USUARIO_CONSULTAR','USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByNombreAerolineaContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(AerolineaService.findByNombreAerolineaContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/nombre_responsable/{value}")//Puede que aqui sea nombreCompleto
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas por el nombre del responsable", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    //@PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    public ResponseEntity<?> findByNombreResponsableContainingIgnoreCase(@PathVariable(value = "value") String value) {
        try {
            return new ResponseEntity<>(AerolineaService.findByNombreResponsableContainingIgnoreCase(value), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
