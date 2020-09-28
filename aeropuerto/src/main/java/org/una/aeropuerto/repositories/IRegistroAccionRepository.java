/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.RegistrosAcciones;

/**
 *
 * @author colo7
 */
public interface IRegistroAccionRepository extends JpaRepository<RegistrosAcciones, Long> {
    
    @Query("SELECT u FROM RegistrosAcciones u LEFT JOIN u.usuarioId d WHERE  d.id=:id")
    public List<RegistrosAcciones> findByUsuarioId(Long id);

    public List<RegistrosAcciones> findByFechaRegistro(@Param("fechaRegistro") Date fecha);

    public List<RegistrosAcciones> findByFechaRegistroBetween(@Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

}
