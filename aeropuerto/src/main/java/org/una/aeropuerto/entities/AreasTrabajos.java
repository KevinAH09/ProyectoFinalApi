/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */
@Entity
@Table(name = "Areas_trabajos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AreasTrabajos implements Serializable {
     
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_area_trabajo", length = 50, unique=true)
    private String nombreAreaTrabajo;

    @Column(length = 150)
    private String descripcion;

    @Column
    private boolean estado;
    
    
     @PrePersist
    public void prePersist() {
        estado = true;
    }
    
    
}
