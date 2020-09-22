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
@Table(name = "Control_gasto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ControlGasto {
    
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String responsable;
    
    @Column(length = 50,name="empresa_contratante")
    private String empresaContratante;
    
    @Column(length = 50,name="numero_contrato")
    private String numeroContrato;
    
    @Column(length = 50)
    private String estado;
    
    @Column(length = 50,name="estado_pago")
    private String estadoPago;
    
    @ManyToOne
    @JoinColumn(name = "area_trabajo_id")
    private AreaTrabajo areaTrabajoId;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
    }
    
}
