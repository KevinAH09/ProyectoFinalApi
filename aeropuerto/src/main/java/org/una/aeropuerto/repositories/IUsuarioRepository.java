/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Usuario;

/**
 *
 * @author colo7
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    
    

    public Usuario findByCedulaAndContrasenaEncriptada(@Param("cedula") String cedula,@Param("contrasenaEncriptada") String contrasenaEncriptada);

    public List<Usuario> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);

    public Usuario findByCedula(String cedula);
    
    public List<Usuario> findByEstado(boolean estado);
    
    @Query("SELECT u FROM Usuario u LEFT JOIN u.rolId d WHERE  d.id=:id")
    public List<Usuario>  findByRolId(@Param("id") Long rolId);
    
    @Query("SELECT u FROM Usuario u LEFT JOIN u.areaTrabajoId d WHERE  d.id=:id")
    public List<Usuario>  findByAreaTrabajoId(@Param("id") Long areaTrabajoId);
}
