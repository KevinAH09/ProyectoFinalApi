/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.components;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.loaders.Roles;
import org.una.aeropuerto.service.IRolService;
import org.una.aeropuerto.service.IUsuarioService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IRolService rolService;


    @Override
    public void run(ApplicationArguments args) {
         System.out.println("org.una.tramites.utils.ConversionLista.findList() wqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        if (usuarioService.findByCedula(cedula).isEmpty()) {
            RolDTO rolDTO;
            final String codigo = "ROLE_ADMIN";
            Optional<RolDTO> rolBuscado = rolService.findByCodigo(codigo);

            if (rolBuscado.isEmpty()) {
                rolDTO = new RolDTO();
                rolDTO.setCodigo(codigo);
                rolDTO.setDescripcion("ROL_ADMINISTRADOR");
                rolDTO = rolService.create(rolDTO);

            } else {
                rolDTO = rolBuscado.get();
            }
            
            createRoles();
            rolBuscado = rolService.findByCodigo(codigo);
            
            UsuarioDTO usuario = new UsuarioDTO();
            Rol rol = MapperUtils.EntityFromDto(rolBuscado.get(), Rol.class);
            usuario.setNombreCompleto("Usuario Admin");
            usuario.setCedula(cedula);
            usuario.setContrasenaEncriptada(password);
            usuario.setRolId(rol);
            usuario = usuarioService.create(usuario);

//         

            System.out.println("Se agrega el usuario inicial");
        } else {
            
            System.out.println("Se encontro el admin");
        }
       

    }
    
    private void createRoles() {
        for (Roles rol : Roles.values()) {
            RolDTO nuevoPermiso = new RolDTO();
            nuevoPermiso.setCodigo(rol.getCodigo());
            nuevoPermiso.setDescripcion(rol.name());
            rolService.create(nuevoPermiso);
        } 
    }
}

