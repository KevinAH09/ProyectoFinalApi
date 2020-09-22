/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Usuario;

/**
 *
 * @author colo7
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    
    

    public Usuario findByCedulaAndPasswordEncriptado(@Param("cedula") String cedula,@Param("passwordEncriptado") String passwordEncriptado);

    public List<Usuario> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);

    public Usuario findByCedula(String cedula);
    
    public List<Usuario> findByEstado(boolean estado);
    
    public List<Usuario>  findByRolId(@Param("rol_id") Long id);
    
    public List<Usuario>  findByAreaTrabajoId(@Param("area_trabajo_id") Long id);
}
