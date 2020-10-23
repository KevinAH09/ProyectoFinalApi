/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.UsuariosDTO;

/**
 *
 * @author colo7
 */
public interface IUsuarioService {
    
    
    public Optional<List<UsuariosDTO>> findAll();

    public Optional<UsuariosDTO> findById(Long id);

    public Optional<List<UsuariosDTO>> findByEstado(boolean estado);

    public Optional<UsuariosDTO> findByCedulaAndContrasenaEncriptado(String cedula, String password);

//    public Optional<List<UsuariosDTO>> findByCedulaAproximate(String cedula);
    
    public Optional<UsuariosDTO> findByCedula(String cedula);

    public Optional<List<UsuariosDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public UsuariosDTO create(UsuariosDTO usuario);

    public Optional<UsuariosDTO> update(UsuariosDTO usuario, Long id);
    
    public Optional<UsuariosDTO> updateContrasena(UsuariosDTO usuario, Long id);

    public void delete(Long id);

    public void deleteAll();

    
    
//    public  login(Usuario usuario); 

    public Optional<List<UsuariosDTO>> findByRolId(Long id);

    public Optional<List<UsuariosDTO>>  findByAreaTrabajoId(Long id);
    
}
