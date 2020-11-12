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
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.entities.Usuarios;
import org.una.aeropuerto.service.IUsuarioService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/usuario")
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuariosDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Usuario", response = UsuariosDTO.class, tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/cedula/{cedula}")
    @ApiOperation(value = "Obtiene un Usuario por la cedula", response = UsuariosDTO.class, tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByCedula(@PathVariable(value = "cedula") String ced) {
        try {
            return new ResponseEntity(usuarioService.findByCedula(ced), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//  

    @GetMapping("/cedula/{cedula}/password_encriptado/{password}")
    @ApiOperation(value = "Obtiene un Usuario por cedula y password", response = UsuariosDTO.class, tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByCedulaAndPassword(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String pass) {
        try {
            return new ResponseEntity<>(usuarioService.findByCedulaAndContrasenaEncriptado(cedula, pass), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuariosDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(usuarioService.findByNombreCompletoAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los usuarios por estado", response = UsuariosDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(usuarioService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Usuario", response = UsuariosDTO.class, tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GESTOR_RRHH')")
    public ResponseEntity<?> create(@Valid @RequestBody UsuariosDTO usuariosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                System.out.println(usuariosDTO);
                return new ResponseEntity(usuarioService.create(usuariosDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un usuario", response = UsuariosDTO.class, tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GESTOR_RRHH')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuariosDTO usuarios, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UsuariosDTO> usuarioUpdated = usuarioService.update(usuarios, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
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
    @PutMapping("/cambioContrasena/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un usuario", response = UsuariosDTO.class, tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GESTOR_RRHH')")
    public ResponseEntity<?> updateCambioContrasena(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuariosDTO usuarios, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UsuariosDTO> usuarioUpdated = usuarioService.updateContrasena(usuarios, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
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

    @GetMapping("/rol/{id}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por Id del rol", response = UsuariosDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByRolId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findByRolId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/areaTrabajo/{term}")//Puede que aqui sea nombreCompleto
    @ApiOperation(value = "Obtiene una lista de todos los usuarios por area de trabajo", response = UsuariosDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasRole('ROLE_GERENTE_RRHH') or hasRole('ROLE_GESTOR_RRHH') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findJefeByAreaTrabajo(@PathVariable(value = "term") Long id) {
        try {
            return new ResponseEntity(usuarioService.findByAreaTrabajoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
