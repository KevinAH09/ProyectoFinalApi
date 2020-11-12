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
import org.una.aeropuerto.dto.ControlesGastosDTO;
import org.una.aeropuerto.dto.DetallesControlesGastosDTO;
import org.una.aeropuerto.service.IDetalleControlGastoService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/detalleControlGasto")
@Api(tags = {"Detalle de control de gastos de mantenimiento"})
public class DetalleControlGastoController {
    
    

    @Autowired
    private IDetalleControlGastoService detalleControlGastoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los detalle de gastos por mantenimento", response = DetallesControlesGastosDTO.class, responseContainer = "List", tags = "Detalle de control de gastos de mantenimiento")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(detalleControlGastoService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un detalle de gasto por mantenimento ", response = DetallesControlesGastosDTO.class, tags = "Detalle de control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(detalleControlGastoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    

    @GetMapping("/tipoServicio/{term}")
    @ApiOperation(value = "Obtiene una lista de datalles de gastos de mantenimento por tipo de servicio", response = DetallesControlesGastosDTO.class, responseContainer = "List", tags = "Detalle de control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByTipoServicio(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(detalleControlGastoService.findByTipoServicio(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un detalle de control de gastos de mantenimiento", response = DetallesControlesGastosDTO.class, tags = "Detalle de control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR_GAST_MANT')")
    public ResponseEntity<?> create(@Valid @RequestBody DetallesControlesGastosDTO detallescontrolesGastosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(detalleControlGastoService.create(detallescontrolesGastosDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un detalle de control de gastos de mantenimiento ", response = DetallesControlesGastosDTO.class, tags = "Detalle de control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR_GAST_MANT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody DetallesControlesGastosDTO detallescontrolesGastosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<DetallesControlesGastosDTO> detalleControlGastoUpdated = detalleControlGastoService.update(detallescontrolesGastosDTO, id);
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
@GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por estado(Valida o Anulada)", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(detalleControlGastoService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/estadoPago/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por estado de pago(Cancelado o Pendiente)", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstadoPago(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(detalleControlGastoService.findByEstadoPago(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/areaTrabajo/{id}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por area trabajo", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByAreaTrabajo(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(detalleControlGastoService.findByAreaTrabajoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
