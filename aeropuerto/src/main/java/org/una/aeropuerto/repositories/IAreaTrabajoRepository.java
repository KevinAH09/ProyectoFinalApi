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
import org.una.aeropuerto.entities.AreaTrabajo;

/**
 *
 * @author colo7
 */
public interface IAreaTrabajoRepository extends JpaRepository<AreaTrabajo, Long>{
    
    public List<AreaTrabajo> findByEstado(boolean estado);
    
    
    public List<AreaTrabajo> findByNombreAreaTrabajoContainingIgnoreCase(@Param("nombreAreaTrabajo")String nombreAreaTrabajo);
    
    @Query("SELECT u FROM AreaTrabajo u WHERE u.nombreAreaTrabajo=:nombreAreaTrabajo")
    public AreaTrabajo findByNombreAreaTrabajo(@Param("nombreAreaTrabajo")String nombreAreaTrabajo);
}
