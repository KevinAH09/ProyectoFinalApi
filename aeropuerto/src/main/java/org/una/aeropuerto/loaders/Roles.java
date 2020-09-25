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

   
    ROL_GERENTE("ROLE_GERENTE"),
    ROL_AUDITOR("ROLE_AUDITOR"),
    ROL_GESTOR("ROLE_GESTOR");
    

    private String codigo;

    Roles(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
