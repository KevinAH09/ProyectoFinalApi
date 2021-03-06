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
@Table(name = "Controles_gastos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ControlesGastos {
    
    
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
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @ManyToOne
    @JoinColumn(name = "detalle_control_gasto_id")
    private DetallesControlesGastos detalleControlGastoId;
    

    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
    }
    
}
