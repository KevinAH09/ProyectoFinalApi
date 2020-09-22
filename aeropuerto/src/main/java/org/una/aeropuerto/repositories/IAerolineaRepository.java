/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Aerolinea;

/**
 *
 * @author Bosco
 */
public interface IAerolineaRepository  extends JpaRepository<Aerolinea, Long> {
    
    public List<Aerolinea> findByEstadoContaining(boolean estado);
    
    public List<Aerolinea> findByNombreAerolineaContainingIgnoreCase(@Param("nombreAerolinea") String nombreAerolinea);

    public List<Aerolinea> findByNombreResponsableContainingIgnoreCase(@Param("nombreResponsable") String nombreResponsable);
   


}
