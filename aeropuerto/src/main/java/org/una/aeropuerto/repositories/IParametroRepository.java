/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Parametros;

/**
 *
 * @author Bosco
 */
public interface IParametroRepository extends JpaRepository<Parametros, Long>{
    
    public List<Parametros> findByEstado(boolean estado);


    public Parametros findByNombreParametro(@Param("nombreParametros") String nombreParametros);
}
