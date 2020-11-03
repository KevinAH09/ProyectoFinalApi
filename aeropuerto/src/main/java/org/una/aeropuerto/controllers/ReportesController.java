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

    @GetMapping("/gastosMant/fechaini/fechafin/{fechaIni}/{fechaFin}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportGastosMantFechas(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteGastosMantFechas(dateIni, dateFin).get();
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
    @GetMapping("/gastosMant/fechaini/fechafin/empresa/{fechaIni}/{fechaFin}/{nombre}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportGastosMAntFechasEmpresa(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin,@PathVariable(value = "nombre") String nombre) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteGastosMantFechasEmpresa(nombre,dateIni, dateFin).get();
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
    @GetMapping("/gastosMant/fechaini/fechafin/area/{fechaIni}/{fechaFin}/{area}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportGastosMantFechasAreaTrabajo(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin,@PathVariable(value = "area") Long area) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteGastosMantFechasAreaTrabajo(dateIni, dateFin,area).get();
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
    @GetMapping("/gastosMant/fechaini/fechafin/pago/{fechaIni}/{fechaFin}/{pago}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportGastosMantFechasEstadoPago(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin,@PathVariable(value = "pago") String pago) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteGastosMantFechasEstadoPago(dateIni, dateFin,pago).get();
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
    @GetMapping("/aviones/fechaini/fechafin/{fechaIni}/{fechaFin}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportAvionesFechas(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteAvionesFechas(dateIni, dateFin).get();
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
    @GetMapping("/aviones/fechaini/fechafin/aerolinea/{fechaIni}/{fechaFin}/{id}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportAvionesAerolinea(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin,@PathVariable(value = "id") Long id) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteAvionesFechasAerolinea(dateIni, dateFin,id).get();
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
    @GetMapping("/aviones/fechaini/fechafin/zona/{fechaIni}/{fechaFin}/{id}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportAvionesZona(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin,@PathVariable(value = "id") Long id) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteAvionesFechasZona(dateIni, dateFin,id).get();
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
    @GetMapping("/aviones/fechaini/fechafin/zonaAerolinea/{fechaIni}/{fechaFin}/{idaero}/{idzona}")
    @ApiOperation(value = "", response = String.class, tags = "Reportes")
    public @ResponseBody
    ResponseEntity<?> reportAvionesZonaAerolinea(@PathVariable(value = "fechaIni") String fechaIni, @PathVariable(value = "fechaFin") String fechaFin,@PathVariable(value = "idaero") Long aero,@PathVariable(value = "idzona") Long zona) {
        try {
            Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIni);
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            System.out.println(dateIni+" "+dateFin);
            byte[] jasperReport;
            JasperPrint jasperPrint = reportesService.reporteAvionesFechasZonaAerolinea(dateIni, dateFin,zona,aero).get();
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
