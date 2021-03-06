/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Zonas;

/**
 *
 * @author Bosco
 */
public interface IZonaRepository extends JpaRepository<Zonas, Long>{
    
    public List<Zonas> findByEstado(boolean estado);
    
    public List<Zonas> findByNombreZonaContainingIgnoreCase(@Param("nombreZonas") String nombreZonas);

    public List<Zonas> findByCodigo(@Param("codigo") String codigo);
}
