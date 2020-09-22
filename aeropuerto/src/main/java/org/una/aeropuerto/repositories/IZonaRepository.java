/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Zona;

/**
 *
 * @author Bosco
 */
public interface IZonaRepository extends JpaRepository<Zona, Long>{
    
    public List<Zona> findByEstadoContaining(boolean estado);
    
    public List<Zona> findByNombreZonaContainingIgnoreCase(@Param("nombreZona") String nombreZona);

    public List<Zona> findByCodigo(@Param("codigo") String codigo);
}
