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
import org.una.aeropuerto.service.IControlGastoService;
import java.text.SimpleDateFormat;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/controlGasto")
@Api(tags = {"Control de gastos de mantenimiento"})
public class ControlGastoController {

    @Autowired
    private IControlGastoService controlGastoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los gastos por mantenimento", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(controlGastoService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un gasto por mantenimento", response = ControlesGastosDTO.class, tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(controlGastoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/empresa/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los gastos de mantenimento", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEmpresaContratanteContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(controlGastoService.findByEmpresaContratanteContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tipo/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los gastos de mantenimento", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByTipoServicio(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(controlGastoService.findByTipoServicio(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los gastos de mantenimento", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByEstadoPago(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(controlGastoService.findByEstadoPago(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un control de gastos de mantenimiento", response = ControlesGastosDTO.class, tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR_GAST_MANT')")
    public ResponseEntity<?> create(@Valid @RequestBody ControlesGastosDTO controlesGastosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(controlGastoService.create(controlesGastosDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un control de gastos de mantenimiento ", response = ControlesGastosDTO.class, tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GESTOR_GAST_MANT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ControlesGastosDTO controlesGastosDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ControlesGastosDTO> controlGastoUpdated = controlGastoService.update(controlesGastosDTO, id);
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
    @ApiOperation(value = "Obtiene una lista de control de gastos de mantenimiento por numero de contrato", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByNumerocontrato(@PathVariable(value = "numeroContrato") String contrato) {
        try {
            return new ResponseEntity(controlGastoService.findByNumeroContrato(contrato), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detalleControlGasto/{id}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por detalle de control gasto", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByDetalleControlGasto(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(controlGastoService.findByDetalleControlGastoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fechaRegistro/{date}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por fecha de registro", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "date") String fecha) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            return new ResponseEntity<>(controlGastoService.findByFechaRegistro(date), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fechaRegistroBetween/{dateStar}/and/{dateEnd}")
    @ApiOperation(value = "Obtiene una lista de todos los control de gastos de mantenimiento por rango de fechas de registro", response = ControlesGastosDTO.class, responseContainer = "List", tags = "Control de gastos de mantenimiento")
    @PreAuthorize("hasRole('ROLE_GERENTE_GAST_MANT') or hasRole('ROLE_AUDITOR') or hasRole('ROLE_GESTOR_GAST_MANT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "dateStar") String fechaStar, @PathVariable(value = "dateEnd") String fechaEnd) {
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStar);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaEnd);
            return new ResponseEntity<>(controlGastoService.findByFechaRegistroBetween(date1, date2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
