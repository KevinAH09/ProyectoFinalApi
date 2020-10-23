/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.loaders;

/**
 *
 * @author colo7
 */
public enum AreasTrabajo {
    
    
    OPERACIONES_AEROLINEAS("_OPER_AERO"),
    GASTOS_MANTENIMENTO("_GAST_MANT");

    private String nombre;

    AreasTrabajo(String codigo) {
        this.nombre = codigo;
    }

    public String getCodigo() {
        return nombre;
    }

}
