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
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.AreasTrabajosDTO;
import org.una.aeropuerto.service.IAreaTrabajoService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/areaTrabajo")
@Api(tags = {"Area de trabajo"})
public class AreaTrabajoController {

    @Autowired
    private IAreaTrabajoService areaService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las areas de trabajo", response = AreasTrabajosDTO.class, responseContainer = "List", tags = "Area de trabajo")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(areaService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un area de trabajo", response = AreasTrabajosDTO.class, tags = "Area de trabajo")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(areaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/nombre_area_trabajo/{nombre}")
    @ApiOperation(value = "Obtiene un area de trabajo por codigo", response = AreasTrabajosDTO.class, responseContainer = "List", tags = "Area de trabajo")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String codigo) {
        try {
            return new ResponseEntity(areaService.findByNombreAreaTrabajoContainingIgnoreCase(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos las areas de trabajo por estado", response = AreasTrabajosDTO.class, responseContainer = "List", tags = "Area de trabajo")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(areaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear una area de trabajo", response = AreasTrabajosDTO.class, tags = "Area de trabajo")
    @PreAuthorize("hasRole('ROLE_GESTOR_RRHH')")
    public ResponseEntity<?> create(@Valid @RequestBody AreasTrabajosDTO areasTrabajosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(areaService.create(areasTrabajosDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica una area de trabajo", response = AreasTrabajosDTO.class, tags = "Area de trabajo")
    @PreAuthorize("hasRole('ROLE_GESTOR_RRHH')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AreasTrabajosDTO areasTrabajosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AreasTrabajosDTO> areaTrabajoUpdated = areaService.update(areasTrabajosDTO, id);
                if (areaTrabajoUpdated.isPresent()) {
                    return new ResponseEntity(areaTrabajoUpdated, HttpStatus.OK);
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



}
