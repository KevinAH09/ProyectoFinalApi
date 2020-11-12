/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.una.aeropuerto.dto.VuelosDTO;
import org.una.aeropuerto.service.IVueloService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/vuelo")
@Api(tags = {"Vuelos"})
public class VueloController {
    @Autowired
    private IVueloService VueloService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de los vuelos por id", response = VuelosDTO.class, tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(VueloService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas los vuelos", response = VuelosDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(VueloService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_GESTOR_OPER_AERO')")
    public ResponseEntity<?> create(@Valid @RequestBody VuelosDTO vuelosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                System.out.println(vuelosDTO);
                return new ResponseEntity(VueloService.create(vuelosDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody VuelosDTO VuelosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<VuelosDTO> VueloUpdated = VueloService.update(VuelosDTO, id);
                if (VueloUpdated.isPresent()) {
                    return new ResponseEntity(VueloUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Obtiene una lista de todas los vuelos por estado", response = VuelosDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(VueloService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/origen/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los vuelos por el origen del vuelo", response = VuelosDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByOrigen(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(VueloService.findByOrigen(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/destino/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los vuelos por el destino del vuelo", response = VuelosDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByDestino(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(VueloService.findByDestino(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/avion/{id}")
    @ApiOperation(value = "Obtiene una lista de vuelos por Id del avion", response = VuelosDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByAvionId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(VueloService.findByAvionId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/bitacora_vuelo/{id}")
    @ApiOperation(value = "Obtiene una lista de vuelos por Id de la bitacora de vuelo", response = VuelosDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findBybitacoraVueloId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(VueloService.findBybitacoraVueloId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fechaInicio/{date}")
    @ApiOperation(value = "Obtiene una lista de todos los registros accion por fecha de registro", response = VuelosDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('ROLE_GERENTE_OPER_AERO') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_OPER_AERO') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaInicio(@PathVariable(value = "date") String fecha) {
        try {
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            return new ResponseEntity<>(VueloService.findByFechaInicio(date2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
