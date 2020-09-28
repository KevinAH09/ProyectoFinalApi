/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Roles;

/**
 *
 * @author colo7
 */
public interface IRolRepository  extends JpaRepository<Roles, Long>{
    
    
    
    public List<Roles> findByEstado(boolean estado);
    
    public Roles findByCodigo(@Param("codigo")String nombreRoles);
    
}
