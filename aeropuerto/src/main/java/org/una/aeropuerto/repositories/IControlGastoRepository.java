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
import org.una.aeropuerto.entities.ControlGasto;

/**
 *
 * @author colo7
 */
public interface IControlGastoRepository extends JpaRepository<ControlGasto, Long> {
    
    public List<ControlGasto> findByEmpresaContratanteContainingIgnoreCase(@Param("empresa_contratante") String empresa);
    
    public List<ControlGasto> findByFechaRegistro(@Param("fecha_registro") Date fecha);

    public List<ControlGasto> findByFechaRegistroBetween(@Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

    public ControlGasto findByNumeroContrato(@Param("numero_contrato") String numeroContrato);
    
    public List<ControlGasto> findByEstado(String estado);
    
    public List<ControlGasto>  findByAreaTrabajoId(@Param("area_trabajo_id") Long id);
    
}
