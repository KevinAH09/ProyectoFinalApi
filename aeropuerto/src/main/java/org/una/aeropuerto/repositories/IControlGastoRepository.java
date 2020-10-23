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
import javax.persistence.TemporalType;
import org.springframework.data.jpa.repository.Temporal;

/**
 *
 * @author colo7
 */
public interface IControlGastoRepository extends JpaRepository<ControlesGastos, Long> {

    public List<ControlesGastos> findByEmpresaContratanteContainingIgnoreCase(@Param("empresaContratante") String empresa);

    @Query("select u from ControlesGastos u where u.fechaRegistro=fechaRegistro")
    public List<ControlesGastos> findByFechaRegistro(@Param("fechaRegistro") Date fecha);

    public List<ControlesGastos> findByFechaRegistroBetween(@Temporal(TemporalType.DATE) Date fechaRegistro1, @Temporal(TemporalType.DATE) Date fechaRegistro2);

    public ControlesGastos findByNumeroContrato(@Param("numeroContrato") String numeroContrato);

    @Query("SELECT u FROM ControlesGastos u LEFT JOIN u.detalleControlGastoId d WHERE  d.id=:id")
    public ControlesGastos findByDetalleControlGastoId(Long id);

    @Query("SELECT u FROM ControlesGastos u LEFT JOIN u.detalleControlGastoId d WHERE  d.tipoServicio=:tipo")
    public List<ControlesGastos> findByTipoServicio(@Param("tipo") String tipo);

    @Query("SELECT u FROM ControlesGastos u LEFT JOIN u.detalleControlGastoId d WHERE  d.estadoPago=:estado")
    public List<ControlesGastos> findByEstadoPago(@Param("estado") String estado);
}
