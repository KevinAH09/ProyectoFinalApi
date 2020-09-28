/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
 * @author colo7
 */
@Entity
@Table(name = "Usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", length = 50)
    private String nombreCompleto;

    @Column(length = 100, name = "contrasena_encriptada")
    private String contrasenaEncriptada;

    @Column(length = 10, unique = true)
    private String cedula;
    
    @Column(length = 50, unique = true)
    private String correo;
 
    @Column
    private boolean estado;
    
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Roles rolId;
    
    @ManyToOne
    @JoinColumn(name = "area_trabajo_id")
    private AreasTrabajos areaTrabajoId;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;


    @Column(name = "jefe_id")
    private boolean jefeId;

    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado = true;
//        esJefe = false;
        fechaRegistro = new Date();
//        fechaModificacion = new Date();
    }

    
    
}

