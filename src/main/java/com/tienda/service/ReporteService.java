/*
IQ 2022
Leonel Solis
Servicio para generacion de reporte jasper
Proyecto tienda
 */
package com.tienda.service;

import com.tienda.entity.Persona;
import com.tienda.repository.PersonaRepository;
import com.tienda.repository.ReporteRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository repository;


    public String exportReporte(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\LeoSolis\\Documents\\ReporteJasper";
        List<Persona> persona = repository.findAll();
        //Carga de archivo y compilacion
        File file = ResourceUtils.getFile("classpath:reporte_personas.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(persona);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("creado con", "Jasper+SpringBoot");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\personas.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\personas.pdf");
        }

        return "Reporte de personas guardado en : " + path;
    }
}
