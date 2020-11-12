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
import org.una.aeropuerto.dto.AerolineasDTO;
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
    @ApiOperation(value = "Obtiene una lista de Aerolineas por id", response = AerolineasDTO.class, tags = "Aerolineas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AerolineaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas", response = AerolineasDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_GESTOR_OPER_AERO')")
    public ResponseEntity<?> create(@Valid @RequestBody AerolineasDTO AerolineasDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(AerolineaService.create(AerolineasDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AerolineasDTO aerolineasDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AerolineasDTO> aerolineaUpdated = AerolineaService.update(aerolineasDTO, id);
                if (aerolineaUpdated.isPresent()) {
                    return new ResponseEntity(aerolineaUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas por estado", response = AerolineasDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(AerolineaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre_aerolinea/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas por nombre de la aerolinea", response = AerolineasDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByNombreAerolineaContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(AerolineaService.findByNombreAerolineaContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/nombre_responsable/{value}")
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas por el nombre del responsable", response = AerolineasDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByNombreResponsableContainingIgnoreCase(@PathVariable(value = "value") String value) {
        try {
            return new ResponseEntity<>(AerolineaService.findByNombreResponsableContainingIgnoreCase(value), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
