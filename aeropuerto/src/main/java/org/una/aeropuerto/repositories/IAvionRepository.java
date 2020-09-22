/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Avion;

/**
 *
 * @author Bosco
 */
public interface IAvionRepository extends JpaRepository<Avion, Long>{
    
    public List<Avion> findByEstadoContaining(boolean estado);
    
    public List<Avion> findByMatriculaContainingIgnoreCase(@Param("Matricula") String matricula);

    public List<Avion> findBytipoAvionContainingIgnoreCase(@Param("tipoAvion") String tipoAvion);
    
    public List<Avion> findByAerolineaId(Long id);
    
}
