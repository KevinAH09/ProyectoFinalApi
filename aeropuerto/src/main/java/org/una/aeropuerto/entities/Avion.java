/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "avion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Avion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricula", length = 50)
    private String matricula;

    @Column(length = 50, name = "tipoAvion")
    private String tipoAvion;

    @Column(length = 30, name = "horasVuelo")
    private String horasVuelo;
    
    @ManyToOne
    @JoinColumn(name = "aerolineaId")
    private Aerolinea aerolineaId;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avionId")
//    private List<Avion> vuelo = new ArrayList<>();
    
    @Column
    private boolean estado;
    
    private static final long serialVersionUID = 1L;
    
    @PrePersist
    public void prePersist() {
        estado = true;
    }
}
