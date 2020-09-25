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
import org.una.aeropuerto.entities.AvionZona;

/**
 *
 * @author Bosco
 */
public interface IAvionZonaRepository extends JpaRepository<AvionZona, Long>{
    
    //@Query("SELECT u FROM Vuelo u LEFT JOIN u.avionId d WHERE  d.id=:id")
    public List<AvionZona> findByAvion(Long avion);
    //@Query("SELECT u FROM Vuelo u LEFT JOIN u.avionId d WHERE  d.id=:id")
    public List<AvionZona> findByZonaId(Long zona);
}
