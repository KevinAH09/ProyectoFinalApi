/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.service;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.UsuarioDTO;

/**
 *
 * @author colo7
 */
public interface IUsuarioService {
    
    
    public Optional<List<UsuarioDTO>> findAll();

    public Optional<UsuarioDTO> findById(Long id);

    public Optional<List<UsuarioDTO>> findByEstado(boolean estado);

    public Optional<UsuarioDTO> findByCedulaAndContrasenaEncriptado(String cedula, String password);

//    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula);
    
    public Optional<UsuarioDTO> findByCedula(String cedula);

    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public UsuarioDTO create(UsuarioDTO usuario);

    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id);

    public void delete(Long id);

    public void deleteAll();

    
    
//    public  login(Usuario usuario); 

    public Optional<List<UsuarioDTO>> findByRolId(Long id);

    public Optional<List<UsuarioDTO>>  findByAreaTrabajoId(Long id);
    
}
