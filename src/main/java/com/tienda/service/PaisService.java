/**
 *
 * @author LeoSolis
 * Clase Service para Pais
 * Proyecto tienda
 * I Q 2022
 */

package com.tienda.service;

import com.tienda.entity.Pais;
import com.tienda.repository.PaisRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService implements IPaisService {
    
   //Inyeccion de depedencias
    @Autowired
    private PaisRepository paisRepository;
    
    @Override
    public List<Pais> listCountry(){
        return (List<Pais>)paisRepository.findAll();
    }
}
