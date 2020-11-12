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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.service.IRolService;
import org.una.aeropuerto.service.IUsuarioService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/rol")
@Api(tags = {"Roles"})
public class RolController {

    @Autowired
    private IRolService rolService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Roles", response = RolesDTO.class, responseContainer = "List", tags = "Roles")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(rolService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Rol", response = RolesDTO.class, tags = "Roles")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(rolService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/codigo/{codigo}")
    @ApiOperation(value = "Obtiene un Rol por codigo", response = RolesDTO.class, tags = "Roles")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "codigo") String codigo) {
        try {
            return new ResponseEntity(rolService.findByCodigo(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los roles por estado", response = RolesDTO.class, responseContainer = "List", tags = "Roles")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(rolService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un rol", response = RolesDTO.class, tags = "Roles")
    @PreAuthorize("hasRole('ROLE_GESTOR_RRHH')")
    public ResponseEntity<?> create(@Valid @RequestBody RolesDTO rolesDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(rolService.create(rolesDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un rol", response = RolesDTO.class, tags = "Roles")
    @PreAuthorize("hasRole('ROLE_GESTOR_RRHH')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RolesDTO rolesDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<RolesDTO> rolUpdated = rolService.update(rolesDTO, id);
                if (rolUpdated.isPresent()) {
                    return new ResponseEntity(rolUpdated, HttpStatus.OK);
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
