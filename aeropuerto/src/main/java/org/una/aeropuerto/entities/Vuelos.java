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
@Table(name = "Vuelos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vuelos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origen", length = 50)
    private String origen;

    @Column(name = "destino", length = 50)
    private String destino;

    @Column(name = "fecha_inical", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaInicio;

    @Column(name = "fecha_final")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;

    @Column
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "avion_Id")
    private Aviones avionId;

    @ManyToOne
    @JoinColumn(name = "bitacora_vuelo_id")
    private BitacorasVuelos bitacoraVueloId;

    private static final long serialVersionUID = 1L;

    @PreUpdate
    public void preUpdate() {

    }

    public void setFechaInicio(Date facheInicio) {
        this.fechaInicio = facheInicio;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
