/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.service.IReportesService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/reportes")
@Api(tags = {"Reportes"})
public class ReportesController {

    @Autowired
    private IReportesService reportesService;

    @GetMapping("/fechaini/fechafin/{fechaIni}/{fechaFin}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> findAll(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin) {
        try {
//            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
//            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteVuelos(new Date(), new Date()).get();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(baos);
            outputStream.writeObject(jasperPrint);
            jasperReport = baos.toByteArray();
            String envioString = Base64.getEncoder().encodeToString(jasperReport);
            System.out.println(envioString);
            return new ResponseEntity<>(envioString, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
