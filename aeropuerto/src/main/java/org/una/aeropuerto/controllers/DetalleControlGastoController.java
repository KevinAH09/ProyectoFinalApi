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
import org.una.aeropuerto.dto.DetalleControlGastoDTO;
import org.una.aeropuerto.service.IDetalleControlGastoService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/Detalle_control_gasto")
@Api(tags = {"Detalle de control de gastos por mantenimiento"})
public class DetalleControlGastoController {
    
    

    @Autowired
    private IDetalleControlGastoService detalleControlGastoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los detalle de gastos por mantenimento", response = DetalleControlGastoDTO.class, responseContainer = "List", tags = "Detalle de control de gastos por mantenimiento")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(detalleControlGastoService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un detalle de gasto por mantenimento ", response = DetalleControlGastoDTO.class, tags = "Detalle de control de gastos por mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(detalleControlGastoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    

    @GetMapping("/tipoServicio/{term}")
    @ApiOperation(value = "Obtiene una lista de datalles de gastos de mantenimento por tipo de servicio", response = DetalleControlGastoDTO.class, responseContainer = "List", tags = "Detalle de control de gastos por mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> findByTipoServicio(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(detalleControlGastoService.findByTipoServicio(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un detalle de control de gastos de mantenimiento", response = DetalleControlGastoDTO.class, tags = "Detalle de control de gastos por mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> create(@Valid @RequestBody DetalleControlGastoDTO detallecontrolGastoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(detalleControlGastoService.create(detallecontrolGastoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un detalle de control de gastos de mantenimiento ", response = DetalleControlGastoDTO.class, tags = "Detalle de control de gastos por mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody DetalleControlGastoDTO detallecontrolGastoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<DetalleControlGastoDTO> detalleControlGastoUpdated = detalleControlGastoService.update(detallecontrolGastoDTO, id);
                if (detalleControlGastoUpdated.isPresent()) {
                    return new ResponseEntity(detalleControlGastoUpdated, HttpStatus.OK);
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