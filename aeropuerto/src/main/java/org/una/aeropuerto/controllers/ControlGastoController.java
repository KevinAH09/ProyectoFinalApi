/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.ControlGastoDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.service.IControlGastoService;
import org.una.aeropuerto.service.IUsuarioService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/Control_gasto")
@Api(tags = {"Control de gastos de mantenimiento"})
public class ControlGastoController {
    

    @Autowired
    private IControlGastoService controlGastoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los gastos por mantenimento", response = ControlGastoDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(controlGastoService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un gasto por mantenimento", response = ControlGastoDTO.class, tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(controlGastoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    

    @GetMapping("/empresa/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los gastos de mantenimento", response = UsuarioDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEmpresaContratanteContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(controlGastoService.findByEmpresaContratanteContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por estado(Valida o Anulada)", response = UsuarioDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(controlGastoService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/estadoPago/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por estado de pago(Cancelado o Pendiente)", response = UsuarioDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstadoPago(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(controlGastoService.findByEstadoPago(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un control de gastos de mantenimiento", response = ControlGastoDTO.class, tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody ControlGastoDTO controlGastoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(controlGastoService.create(controlGastoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un control de gastos de mantenimiento ", response = ControlGastoDTO.class, tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ControlGastoDTO controlGastoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ControlGastoDTO> controlGastoUpdated = controlGastoService.update(controlGastoDTO, id);
                if (controlGastoUpdated.isPresent()) {
                    return new ResponseEntity(controlGastoUpdated, HttpStatus.OK);
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

    @GetMapping("/numeroContrato/{numeroContrato}")
    @ApiOperation(value = "Obtiene una lista de control de gastos de mantenimiento por numero de contrato", response = UsuarioDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByNumerocontrato(@PathVariable(value = "numeroContrato") String contrato) {
        try {
            return new ResponseEntity(controlGastoService.findByNumeroContrato(contrato), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/areaTrabajo/{id}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por area trabajo", response = UsuarioDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByAreaTrabajo(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(controlGastoService.findByAreaTrabajoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fechaRegistro/{date}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por fecha de registro", response = UsuarioDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "date") Date fecha) {
        try {
            return new ResponseEntity<>(controlGastoService.findByFechaRegistro(fecha), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fechaRegistroStar/{dateStar}/fechaRegistroEnd/{dateEnd}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por rango de fechas de registro", response = UsuarioDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "dateStar") Date fechaStar,@PathVariable(value = "dateEnd") Date fechaEnd) {
        try {
            return new ResponseEntity<>(controlGastoService.findByFechaRegistroBetween(fechaStar, fechaEnd), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   
}
