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
import org.una.aeropuerto.dto.ZonaDTO;
import org.una.aeropuerto.service.IZonaService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/zonas")
@Api(tags = {"Zonas"})
public class ZonaController {
    
    @Autowired
    private IZonaService ZonaService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de la zonas por id", response = ZonaDTO.class, tags = "Zonas")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(ZonaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las zonas", response = ZonaDTO.class, responseContainer = "List", tags = "Zonas")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(ZonaService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    //@PreAuthorize("hasAuthority('ARCHIVO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody ZonaDTO ZonaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(ZonaService.create(ZonaDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ZonaDTO ZonaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ZonaDTO> ZonaUpdated = ZonaService.update(ZonaDTO, id);
                if (ZonaUpdated.isPresent()) {
                    return new ResponseEntity(ZonaUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Obtiene una lista de todas las zonas por estado", response = ZonaDTO.class, responseContainer = "List", tags = "Zonas")
    //@PreAuthorize("hasAuthority('USUARIO_INACTIVAR')")
    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(ZonaService.findByEstadoContaining(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre_zona/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las zonas por nombre de la zona", response = ZonaDTO.class, responseContainer = "List", tags = "Zonas")
    //@PreAuthorize("hasAuthority('USUARIO_CONSULTAR','USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByNombreZonaContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(ZonaService.findByNombreZonaContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/codigo/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las zonas por codigo de la zona", response = ZonaDTO.class, responseContainer = "List", tags = "Zonas")
    //@PreAuthorize("hasAuthority('USUARIO_CONSULTAR','USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(ZonaService.findByCodigo(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
}
