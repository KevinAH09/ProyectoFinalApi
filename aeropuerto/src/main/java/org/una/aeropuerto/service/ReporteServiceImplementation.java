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
        try {
            Conexion();
            Map map = new HashMap();
            System.out.println(AeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation());
            URL url = AeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation();
            String rutaUrl = decode(url.toURI().toString());

            String ruta = rutaUrl.replaceFirst("file:/", "");
            String reporte = ruta.replaceAll("/target/classes/", "/src/main/java/org/una/aeropuerto/report/report1.jasper");
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

}
