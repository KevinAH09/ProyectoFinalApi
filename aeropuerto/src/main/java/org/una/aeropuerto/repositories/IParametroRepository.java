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
import org.una.aeropuerto.entities.Avion;
import org.una.aeropuerto.entities.Parametro;

/**
 *
 * @author Bosco
 */
public interface IParametroRepository extends JpaRepository<Parametro, Long>{
    
    public List<Parametro> findByEstadoContaining(boolean estado);


    public List<Parametro> findByNombreParametro(@Param("nombreParametro") String nombreParametro);

    //public List<Parametro> findByNombreValor(@Param("valor") String valor);

    //public Parametro findByCedula(@Param("cedula") String cedula);
    
    //public List<Parametro> findByFechaRegistro(Date fechaRegistro);//arreglar

    
    
}
