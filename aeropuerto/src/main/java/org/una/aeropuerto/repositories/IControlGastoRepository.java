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
import org.una.aeropuerto.entities.ControlGasto;

/**
 *
 * @author colo7
 */
public interface IControlGastoRepository extends JpaRepository<ControlGasto, Long> {
    
    public List<ControlGasto> findByEmpresaContratanteContainingIgnoreCase(@Param("empresaContratante") String empresa);
    
    public List<ControlGasto> findByFechaRegistro(@Param("fechaRegistro") Date fecha);

    public List<ControlGasto> findByFechaRegistroBetween(@Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

    public ControlGasto findByNumeroContrato(@Param("numeroContrato") String numeroContrato);
    
    public List<ControlGasto> findByEstado(String estado);
    
    public List<ControlGasto> findByEstadoPago(String estadoPago);
    
    @Query("SELECT u FROM ControlGasto u LEFT JOIN u.areaTrabajoId d WHERE  d.id=:id")
    public List<ControlGasto>  findByAreaTrabajoId(Long id);
    
}
