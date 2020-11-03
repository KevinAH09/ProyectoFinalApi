/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Bosco
 */
@Entity
@Table(name = "Bitacoras_vuelos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BitacorasVuelos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_bitacora", length = 50)
    private String tipoBitacora;
    
    @Column
    private boolean cargaPasajero;
    @Column
    private boolean cargaCombustible;
    @Column
    private boolean horasVuelo;
    @Column
    private boolean zonaDescarga;
    @Column
    private boolean autorizacionTorreControl;
    
    private static final long serialVersionUID = 1L;
    
}
