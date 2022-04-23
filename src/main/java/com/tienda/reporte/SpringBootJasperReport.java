package com.tienda.reporte;

/*
Clase para generar el reporte jasper
Proyecto tienda
Leonel Solis
IQ 2022
 */


import com.tienda.entity.Persona;
import com.tienda.repository.ReporteRepository;
import com.tienda.service.ReporteService;
import java.io.FileNotFoundException;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LeoSolis
 */
@SpringBootApplication
@RestController
public class SpringBootJasperReport {

    @Autowired
    private ReporteRepository repository;
    @Autowired
    private ReporteService service;

    @GetMapping("/getPersonas")
    public List<Persona> getPersonas() {

        return repository.findAll();
    }

    @GetMapping("/reporte/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReporte(format);
    }

}