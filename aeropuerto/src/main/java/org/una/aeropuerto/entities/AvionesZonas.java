/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Bosco
 */
@Entity
@Table(name = "Aviones_zonas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AvionesZonas implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_ingreso")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    
    @ManyToOne
    @JoinColumn(name = "zona_id")
    private Zonas zona;
    
    @ManyToOne
    @JoinColumn(name = "avion_id")
    private Aviones avion;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaIngreso = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaIngreso = new Date();//no estoy seguro si va
    }
    
}
