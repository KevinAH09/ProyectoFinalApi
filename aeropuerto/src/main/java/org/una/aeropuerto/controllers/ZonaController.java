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
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.una.aeropuerto.dto.ZonasDTO;
import org.una.aeropuerto.service.IZonaService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/zona")
@Api(tags = {"Zonas"})
public class ZonaController {
    
    @Autowired
    private IZonaService ZonaService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de la zonas por id", response = ZonasDTO.class, tags = "Zonas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(ZonaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las zonas", response = ZonasDTO.class, responseContainer = "List", tags = "Zonas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_GESTOR_OPER_AERO')")
    public ResponseEntity<?> create(@Valid @RequestBody ZonasDTO ZonasDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(ZonaService.create(ZonasDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_GESTOR_OPER_AERO')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ZonasDTO ZonasDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ZonasDTO> ZonaUpdated = ZonaService.update(ZonasDTO, id);
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
    @ApiOperation(value = "Obtiene una lista de todas las zonas por estado", response = ZonasDTO.class, responseContainer = "List", tags = "Zonas")
   @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(ZonaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre_zona/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las zonas por nombre de la zona", response = ZonasDTO.class, responseContainer = "List", tags = "Zonas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByNombreZonaContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(ZonaService.findByNombreZonaContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/codigo/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las zonas por codigo de la zona", response = ZonasDTO.class, responseContainer = "List", tags = "Zonas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(ZonaService.findByCodigo(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
}
