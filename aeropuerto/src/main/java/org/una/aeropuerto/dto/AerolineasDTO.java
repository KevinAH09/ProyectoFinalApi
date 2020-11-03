package org.una.aeropuerto.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Bosco
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AerolineasDTO {
    private Long id;
    private String nombreAerolinea;
    private String nombreResponsable;
    private boolean estado;
}
