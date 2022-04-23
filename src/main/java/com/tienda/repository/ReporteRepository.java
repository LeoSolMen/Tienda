/*
Interfase Repositorio para Reporte
Proyecto tienda
 */
package com.tienda.repository;

import com.tienda.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author LeoSolis
 */
public interface ReporteRepository extends JpaRepository<Persona,Integer>  {
    
}
