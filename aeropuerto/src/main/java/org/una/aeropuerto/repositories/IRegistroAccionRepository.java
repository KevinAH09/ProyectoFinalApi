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
import org.una.aeropuerto.entities.RegistroAccion;
import org.una.aeropuerto.entities.Usuario;

/**
 *
 * @author colo7
 */
public interface IRegistroAccionRepository extends JpaRepository<RegistroAccion, Long> {

    public List<RegistroAccion> findByUsuarioId(@Param("usuario_id") Long id);

    public List<RegistroAccion> findByFechaRegistro(@Param("fecha_registro") Date fecha);

    public List<RegistroAccion> findByFechaRegistroBetween(@Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

}
