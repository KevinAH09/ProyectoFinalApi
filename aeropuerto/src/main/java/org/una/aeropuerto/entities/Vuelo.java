/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

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
@Table(name = "vuelo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origen", length = 50)
    private String origen;
    
    @Column(name = "destino", length = 50)
    private String destino;
    
    @Column(name = "fechaInicio", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaInicio;

    @Column(name = "fechaFinal")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;
    
    @Column
    private boolean estado;
    
    @ManyToOne
    @JoinColumn(name = "avionId")
    private Avion avionId;
    
    @ManyToOne
    @JoinColumn(name = "bitacoraVueloId")
    private BitacoraVuelo bitacoraVueloId;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado = true;
        fechaInicio = new Date();
        fechaFinal = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaInicio = new Date();//no estoy seguro si va
        fechaFinal = new Date();
    }
}
