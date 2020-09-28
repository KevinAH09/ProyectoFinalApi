/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Aerolineas;

/**
 *
 * @author Bosco
 */
public interface IAerolineaRepository  extends JpaRepository<Aerolineas, Long> {
    
    public List<Aerolineas> findByEstado(boolean estado);
    
    public List<Aerolineas> findByNombreAerolineaContainingIgnoreCase(@Param("nombreAerolinea") String nombreAerolinea);

    public List<Aerolineas> findByNombreResponsableContainingIgnoreCase(@Param("nombreResponsable") String nombreResponsable);
   


}
