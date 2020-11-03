/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.Date;
import java.util.Optional;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author colo7
 */
public interface IReportesService {
    
    Optional<JasperPrint> reporteVuelos(Date fechaIni, Date fechaFin);
    
    Optional<JasperPrint> reporteGastosMantFechas(Date fechaIni, Date fechaFin);
    
    Optional<JasperPrint> reporteGastosMantFechasAreaTrabajo(Date fechaIni, Date fechaFin,Long id);
    
    Optional<JasperPrint> reporteGastosMantFechasEstadoPago(Date fechaIni, Date fechaFin,String estadoPago);
    
    Optional<JasperPrint> reporteGastosMantFechasEmpresa(String nombre,Date fechaIni, Date fechaFin);
    
}
