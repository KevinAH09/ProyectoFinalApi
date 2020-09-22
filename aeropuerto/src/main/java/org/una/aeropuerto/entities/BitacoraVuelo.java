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
@Table(name = "bitacoraVuelo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BitacoraVuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipoBitacora", length = 50)
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bitacoraVueloId")
    private List<Avion> vuelo = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;
    
    @PrePersist
    public void prePersist() {
        cargaPasajero = true;
        cargaCombustible = true;
        horasVuelo = true;
        zonaDescarga = true;
        autorizacionTorreControl = true;
    }
    
}
