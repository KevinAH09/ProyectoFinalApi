/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Comparator;
import java.util.List;
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
import org.una.aeropuerto.dto.AvionesZonasDTO;
import org.una.aeropuerto.service.IAvionZonaService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/avionZona")
@Api(tags = {"Zonas y Aviones"})
public class AvionZonaController {
    
    @Autowired
    private IAvionZonaService AvionZonaService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de las zonas de los aviones por id", response = AvionesZonasDTO.class, tags = "Zonas y Aviones")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionZonaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las zonas de los aviones", response = AvionesZonasDTO.class, responseContainer = "List", tags = "Zonas y Aviones")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_GESTOR_OPER_AERO')")
    public ResponseEntity<?> create(@Valid @RequestBody AvionesZonasDTO AvionesZonasDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(AvionZonaService.create(AvionesZonasDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AvionesZonasDTO AvionesZonasDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AvionesZonasDTO> AvionZonaUpdated = AvionZonaService.update(AvionesZonasDTO, id);
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
    @ApiOperation(value = "Obtiene una lista de aviones por Id de la zona", response = AvionesZonasDTO.class, responseContainer = "List", tags = "Zonas y Aviones")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByZonaId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionZonaService.findByZonaId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/avion/{id}")
    @ApiOperation(value = "Obtiene una lista de zonas por Id del avion", response = AvionesZonasDTO.class, responseContainer = "List", tags = "Zonas y Aviones")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByAvionId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(AvionZonaService.findByAvion(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/avionZonaReciente/{id}")
    @ApiOperation(value = "Obtiene una lista de zonas por Id del avion y fecha reciente", response = AvionesZonasDTO.class, tags = "Zonas y Aviones")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByAvionIdANDFechaReciente(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<AvionesZonasDTO>> avionesZonasDTO = AvionZonaService.findByAvion(id);
            if (avionesZonasDTO.isPresent()) {
                return new ResponseEntity(avionesZonasDTO.get().stream().max(Comparator.comparing(x -> x.getFechaIngreso())).get(), HttpStatus.OK);
            }else {
                return new ResponseEntity(avionesZonasDTO, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
