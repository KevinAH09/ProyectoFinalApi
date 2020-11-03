package org.una.aeropuerto.dto;
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
public class AreasTrabajosDTO {
     
    
    
    private Long id;
    private String nombreAreaTrabajo;
    private String descripcion;
    private boolean estado;
    
   
    
}
