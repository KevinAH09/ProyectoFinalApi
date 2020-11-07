/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.io.File;
import java.net.URL;
import static java.net.URLDecoder.decode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.AeropuertoApplication;

/**
 *
 * @author colo7
 */
@Service
public class ReporteServiceImplementation implements IReportesService {

    Connection connetion = null;
    Map map = null;

    public void Conexion() {
        try {
            System.out.println("conectado " + connetion);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connetion = DriverManager.getConnection("jdbc:mysql://107.180.0.205:3306/BD_PF_Grupo6", "BD_PF_Grupo6_U", "cYw*}RD$@evB/f[");
            System.out.println("conectado " + connetion);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Optional<JasperPrint> reporteVuelos(Date fechaIni, Date fechaFin) {
        return createReport("report1");
    }

    @Override
    public Optional<JasperPrint> reporteGastosMantFechas(Date fechaIni, Date fechaFin) {
        map = new HashMap();
        map.put("fechafin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportGastosMantFechas");

    }

    private Optional<JasperPrint> createReport(String nombreReport) {
        try {
            Conexion();

            System.out.println(AeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation());
            URL url = AeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation();
            String rutaUrl = decode(url.toURI().toString());

            String ruta = rutaUrl.replaceFirst("file:/", "");
            String reporte = ruta.replaceAll("/target/classes/", "/src/main/java/org/una/aeropuerto/report/" + nombreReport + ".jasper");
            String rutal = reporte.replaceAll("/", "\\\\");
            String rutaReal = rutal.replaceAll("%20", " ");
            System.out.println("59 " + rutaReal);
            File file = new File(rutaReal);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JasperPrint rePrint = JasperFillManager.fillReport(jasperReport, map, connetion);
            return Optional.ofNullable(rePrint);
        } catch (Exception ex) {

            System.out.println("67" + ex);
            return null;
        }

    }

    @Override
    public Optional<JasperPrint> reporteGastosMantFechasEmpresa(String nombre, Date fechaIni, Date fechaFin) {
        map = new HashMap();
        map.put("nombreEmpresa",nombre);
        map.put("fechaFin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportGastosMantFechasEmpresa");
    }

    @Override
    public Optional<JasperPrint> reporteGastosMantFechasAreaTrabajo(Date fechaIni, Date fechaFin, Long id) {
        map = new HashMap();
        map.put("area",id);
        map.put("fechaFin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportGastosMantFechasAreaTrabajo");
    }

    @Override
    public Optional<JasperPrint> reporteGastosMantFechasEstadoPago(Date fechaIni, Date fechaFin, String estadoPago) {
        map = new HashMap();
        map.put("pago",estadoPago);
        map.put("fechaFin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportGastosMantFechasEstadoPago");
    }

    @Override
    public Optional<JasperPrint> reporteAvionesFechas(Date fechaIni, Date fechaFin) {
        map = new HashMap();
        map.put("fechaFin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportRecorridoAvionesFechas");
    }

    @Override
    public Optional<JasperPrint> reporteAvionesFechasAerolinea(Date fechaIni, Date fechaFin, Long id) {
        map = new HashMap();
        map.put("aero",id);
        map.put("fechaFin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportRecorridoAvionesFechasAero");
    }

    @Override
    public Optional<JasperPrint> reporteAvionesFechasZona(Date fechaIni, Date fechaFin, Long id) {
        map = new HashMap();
        map.put("zona",id);
        map.put("fechaFin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportRecorridoAvionesFechasZona");
    }

    @Override
    public Optional<JasperPrint> reporteAvionesFechasZonaAerolinea(Date fechaIni, Date fechaFin, Long zonaId, Long aeroId) {
        map = new HashMap();
        map.put("aero",aeroId);
        map.put("zona",zonaId);
        map.put("fechaFin", fechaFin);
        map.put("fechaIni", fechaIni);
        return createReport("reportRecorridoAvionesFechasZonaAero");
    }
}
