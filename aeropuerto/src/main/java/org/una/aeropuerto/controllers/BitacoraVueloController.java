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
import org.una.aeropuerto.dto.BitacoraVueloDTO;
import org.una.aeropuerto.service.IBitacoraVueloService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/bitacora_vuelo")
@Api(tags = {"Bitacora de vuelos"})
public class BitacoraVueloController {
    
    @Autowired
    private IBitacoraVueloService BitacoraVueloService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de las bitacoras de los vuelos por id", response = BitacoraVueloDTO.class, tags = "Bitacora de vuelos")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(BitacoraVueloService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las bitacoras de los vuelos", response = BitacoraVueloDTO.class, responseContainer = "List", tags = "Bitacora de vuelos")
    //@PreAuthorize("hasAuthority('ARCHIVO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(BitacoraVueloService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    //@PreAuthorize("hasAuthority('ARCHIVO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody BitacoraVueloDTO BitacoraVueloDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(BitacoraVueloService.create(BitacoraVueloDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody BitacoraVueloDTO BitacoraVueloDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<BitacoraVueloDTO> AvionZonaUpdated = BitacoraVueloService.update(BitacoraVueloDTO, id);
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
    
    @GetMapping("/tipo_bitacora/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las bitacoras por el tipo de bitacora", response = BitacoraVueloDTO.class, responseContainer = "List", tags = "Bitacora de vuelos")
    //@PreAuthorize("hasAuthority('USUARIO_CONSULTAR','USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByTipoBitacoraContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(BitacoraVueloService.findByTipoBitacoraContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/autorizacion_torre_control/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las bitacoras de vuelo por autorizacion de la torre de control", response = BitacoraVueloDTO.class, responseContainer = "List", tags = "Bitacora de vuelos")
    //@PreAuthorize("hasAuthority('USUARIO_INACTIVAR')")
    public ResponseEntity<?> findByAutorizacionTorreControl(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(BitacoraVueloService.findByAutorizacionTorreControl(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/cargo_combustible/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las bitacoras de vuelo por cargo de combustible", response = BitacoraVueloDTO.class, responseContainer = "List", tags = "Bitacora de vuelos")
    //@PreAuthorize("hasAuthority('USUARIO_INACTIVAR')")
    public ResponseEntity<?> findByCargaCombustible(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(BitacoraVueloService.findByCargaCombustible(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/carga_pasajero/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las bitacoras de vuelo por carga de pasajeros", response = BitacoraVueloDTO.class, responseContainer = "List", tags = "Bitacora de vuelos")
    //@PreAuthorize("hasAuthority('USUARIO_INACTIVAR')")
    public ResponseEntity<?> findByCargaPasajero(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(BitacoraVueloService.findByCargaPasajero(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/zona_descarga/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las bitacoras de vuelo por zona de descarga", response = BitacoraVueloDTO.class, responseContainer = "List", tags = "Bitacora de vuelos")
    //@PreAuthorize("hasAuthority('USUARIO_INACTIVAR')")
    public ResponseEntity<?> findByZonaDescarga(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(BitacoraVueloService.findByZonaDescarga(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
