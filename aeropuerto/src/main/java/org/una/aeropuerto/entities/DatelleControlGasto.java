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
 * @author colo7
 */
@Entity
@Table(name = "Datalle_control_gasto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DatelleControlGasto {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 150)
    private String observacion;
    
    @Column(length = 50,name="tipo_servicio")
    private String tipoServicio;
    
    @Column
    private Long duracion;
    
    @Column
    private Long periodicidad;
    
    
    @ManyToOne
    @JoinColumn(name = "control_gasto_id")
    private AreaTrabajo controlGastoId;
    
    @PrePersist
    public void prePersist() {
        
    }
    
}
