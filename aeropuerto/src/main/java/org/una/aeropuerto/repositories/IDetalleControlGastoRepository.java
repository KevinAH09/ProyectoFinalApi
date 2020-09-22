/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.DetalleControlGasto;

/**
 *
 * @author colo7
 */
public interface IDetalleControlGastoRepository extends JpaRepository<DetalleControlGasto, Long> {
    
    public DetalleControlGasto findByControlGastoId(@Param("control_gasto_id") String cedula);

    public List<DetalleControlGasto> findByTipoServicio(@Param("tipo_servicio") String tipoServicio);
    
    public List<DetalleControlGasto>  findByDuracion(@Param("duracion") Long duracion);
    
}
