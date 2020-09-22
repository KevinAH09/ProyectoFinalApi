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
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author colo7
 */
public interface IRolRepository  extends JpaRepository<Rol, Long>{
    
    
    
    public List<Rol> findByEstado(boolean estado);
    
    public Rol findByCodigo(@Param("codigo")String nombreRol);
    
}
