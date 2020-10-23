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
import org.una.aeropuerto.dto.AreasTrabajosDTO;
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.entities.AreasTrabajos;
import org.una.aeropuerto.loaders.AreasTrabajo;
import org.una.aeropuerto.loaders.Roles;
import org.una.aeropuerto.service.IAreaTrabajoService;
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
    @Autowired
    private IAreaTrabajoService areaTrabajoService;

    RolesDTO rolDTO;
    AreasTrabajosDTO areaTrabajoDTO;

    @Override
    public void run(ApplicationArguments args) {
        if (usuarioService.findByCedula(cedula).isEmpty()) {
            bucarRol();
            bucarAreaTrabajo();
            UsuariosDTO usuario = new UsuariosDTO();
            usuario.setNombreCompleto("Usuario Inicial");
            usuario.setCedula(cedula);
            usuario.setContrasenaEncriptada(password);
            usuario.setRolId(rolDTO);
            usuario.setAreaTrabajoId(areaTrabajoDTO);
            usuario = usuarioService.create(usuario);

//         
            System.out.println("Se agrega el usuario inicial");
        } else {

            System.out.println("Se encontro el admin");
        }

    }

    private void createRoles() {
        for (Roles rol : Roles.values()) {
            RolesDTO nuevoPermiso = new RolesDTO();
            nuevoPermiso.setCodigo(rol.getCodigo());
            nuevoPermiso.setDescripcion(rol.name());
            rolService.create(nuevoPermiso);
        }
    }

    private void createAreas() {
        for (AreasTrabajo are : AreasTrabajo.values()) {
            AreasTrabajosDTO nuevoArea = new AreasTrabajosDTO();
            nuevoArea.setNombreAreaTrabajo(are.getCodigo());
            nuevoArea.setDescripcion(are.name());
            areaTrabajoService.create(nuevoArea);
        }
    }

    void bucarRol() {
        final String codigo = "ROLE_GESTOR";
        Optional<RolesDTO> rolBuscado = rolService.findByCodigo(codigo);

        if (rolBuscado.isEmpty()) {
            rolDTO = new RolesDTO();
            rolDTO.setCodigo(codigo);
            rolDTO.setDescripcion("GESTOR");
            rolDTO = rolService.create(rolDTO);
            createRoles();
        } else {
            rolDTO = rolBuscado.get();
        }

    }

    void bucarAreaTrabajo() {
        final String nombre = "_RRHH";
        Optional<AreasTrabajosDTO> areaBuscada = areaTrabajoService.findByNombreAreaTrabajo(nombre);
        if (areaBuscada.isEmpty()) {
            areaTrabajoDTO = new AreasTrabajosDTO();
            areaTrabajoDTO.setNombreAreaTrabajo(nombre);
            areaTrabajoDTO.setDescripcion("RECURSOS_HUMANOS");
            areaTrabajoDTO = areaTrabajoService.create(areaTrabajoDTO);
            createAreas();
        } else {
            areaTrabajoDTO = areaBuscada.get();
        }

    }
}
