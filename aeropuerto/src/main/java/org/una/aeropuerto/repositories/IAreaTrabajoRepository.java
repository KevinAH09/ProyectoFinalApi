/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.AreasTrabajos;

/**
 *
 * @author colo7
 */
public interface IAreaTrabajoRepository extends JpaRepository<AreasTrabajos, Long>{
    
    public List<AreasTrabajos> findByEstado(boolean estado);
    
    
    public List<AreasTrabajos> findByNombreAreaTrabajoContainingIgnoreCase(@Param("nombreAreasTrabajo")String nombreAreaTrabajo);
    
    @Query("SELECT u FROM AreasTrabajos u WHERE u.nombreAreaTrabajo=:nombreAreaTrabajo")
    public AreasTrabajos findByNombreAreaTrabajo(@Param("nombreAreaTrabajo")String nombreAreaTrabajo);
}
