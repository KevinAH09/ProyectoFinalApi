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
public enum Roles {

    
    GERENTE("ROLE_GERENTE"),
    AUDITOR("ROLE_AUDITOR"),
    ADMINISTRADOR("ROLE_ADMIN");
    

    private String codigo;

    Roles(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
