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
import org.una.aeropuerto.entities.Aviones;

/**
 *
 * @author Bosco
 */
public interface IAvionRepository extends JpaRepository<Aviones, Long>{
    
    public List<Aviones> findByEstado(boolean estado);
    
    public List<Aviones> findByMatriculaContainingIgnoreCase(@Param("Matricula") String matricula);
    
    public Aviones findByMatricula(@Param("Matricula") String matricula);

    public List<Aviones> findBytipoAvionContainingIgnoreCase(@Param("tipoAviones") String tipoAviones);
    
    @Query("SELECT u FROM Aviones u LEFT JOIN u.aerolineaId d WHERE  d.id=:id")
    public List<Aviones> findByAerolineaId(Long id);
//    
//    @Query("SELECT u FROM Aviones u LEFT JOIN u.aerolineaId d WHERE  d.aerolineaId=:aerolineaId and u.matricula=:matricula")
//    public List<Aviones> findByAerolineaIdMatricula(@Param("aerolineaId") Long aerolineaId,@Param("matricula") String matricula);
    
   
}
