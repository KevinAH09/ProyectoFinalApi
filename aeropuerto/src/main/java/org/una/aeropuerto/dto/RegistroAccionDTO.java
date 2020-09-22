/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import org.una.aeropuerto.entities.*;
import java.util.Date;
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
public class RegistroAccionDTO {
    
    private Long id;
    private Usuario usuarioId;
    private String accion;
    private Date fechaRegistro;


}
