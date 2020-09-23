/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import org.una.aeropuerto.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class DetalleControlGastoDTO {
   
    private Long id;
    private String observacion;
    private String tipoServicio;
    private Long duracion;
    private Long periodicidad;
    
    
}
