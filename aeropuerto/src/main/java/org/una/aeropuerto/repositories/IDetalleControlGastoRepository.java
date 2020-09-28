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
import org.una.aeropuerto.entities.DetallesControlesGastos;

/**
 *
 * @author colo7
 */
public interface IDetalleControlGastoRepository extends JpaRepository<DetallesControlesGastos, Long> {

    public List<DetallesControlesGastos> findByTipoServicio(@Param("tipoServicio") String tipoServicio);

    public List<DetallesControlesGastos> findByDuracion(@Param("duracion") Long duracion);
    
    public List<DetallesControlesGastos> findByEstado(String estado);
    
    public List<DetallesControlesGastos> findByEstadoPago(String estadoPago);
    
    @Query("SELECT u FROM DetallesControlesGastos u LEFT JOIN u.areaTrabajoId d WHERE  d.id=:id")
    public List<DetallesControlesGastos>  findByAreaTrabajoId(Long id);

}
