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
import org.una.aeropuerto.entities.ControlesGastos;

/**
 *
 * @author colo7
 */
public interface IControlGastoRepository extends JpaRepository<ControlesGastos, Long> {
    
    public List<ControlesGastos> findByEmpresaContratanteContainingIgnoreCase(@Param("empresaContratante") String empresa);
    
    public List<ControlesGastos> findByFechaRegistro(@Param("fechaRegistro") Date fecha);

    public List<ControlesGastos> findByFechaRegistroBetween(@Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

    public ControlesGastos findByNumeroContrato(@Param("numeroContrato") String numeroContrato);
    
    @Query("SELECT u FROM ControlesGastos u LEFT JOIN u.detalleControlGastoId d WHERE  d.id=:id")
    public ControlesGastos  findByDetalleControlGastoId(Long id);
}
