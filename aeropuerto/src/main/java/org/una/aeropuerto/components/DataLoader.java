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
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.service.IUsuarioService;

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


    @Override
    public void run(ApplicationArguments args) {
         System.out.println("org.una.tramites.utils.ConversionLista.findList() wqeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        if (usuarioService.findByCedula(cedula).isEmpty()) {
             
//            PermisoDTO permiso;
//            final String codigo = "USU1";
//            Optional<PermisoDTO> permisoBuscado = permisoService.findByCodigo(codigo);
//
//            if (permisoBuscado.isEmpty()) {
//                permiso = new PermisoDTO();
//                permiso.setCodigo(codigo);
//                permiso.setDescripcion("USUARIO_CREAR");
//                permiso = permisoService.create(permiso);
//
//            } else {
//                permiso = permisoBuscado.get();
//            }
            
//            createPermisos();
            UsuarioDTO usuario = new UsuarioDTO();
            
            usuario.setNombreCompleto("Usuario Admin");
            usuario.setCedula(cedula);
            usuario.setContrasenaEncriptada(password);
            usuario = usuarioService.create(usuario);

//            PermisoOtorgadoDTO permisoOtorgado = new PermisoOtorgadoDTO();
//            permisoOtorgado.setPermisoId(permiso);
//            permisoOtorgado.setUsuarios(usuario);
//            permisoOtorgadoService.create(permisoOtorgado);

            System.out.println("Se agrega el usuario inicial");
        } else {
            
            System.out.println("Se encontro el admin");
        }
       

    }
    
//    private void createPermisos() {
//        for (Permisos permiso : Permisos.values()) {
//            PermisoDTO nuevoPermiso = new PermisoDTO();
//            nuevoPermiso.setCodigo(permiso.getCodigo());
//            nuevoPermiso.setDescripcion(permiso.name());
//            permisoService.create(nuevoPermiso);
//        } 
//    }
}

