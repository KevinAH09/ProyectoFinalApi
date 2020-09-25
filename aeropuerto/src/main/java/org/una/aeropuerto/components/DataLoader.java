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
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.entities.Rol;
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

    RolDTO rolDTO;
    AreaTrabajoDTO areaTrabajoDTO;

    @Override
    public void run(ApplicationArguments args) {
        if (usuarioService.findByCedula(cedula).isEmpty()) {
            bucarRol();
            bucarAreaTrabajo();
            UsuarioDTO usuario = new UsuarioDTO();
            Rol rol = MapperUtils.EntityFromDto(rolDTO, Rol.class);
            AreaTrabajo areaTrabajo = MapperUtils.EntityFromDto(areaTrabajoDTO, AreaTrabajo.class);
            usuario.setNombreCompleto("Usuario Inicial");
            usuario.setCedula(cedula);
            usuario.setContrasenaEncriptada(password);
            usuario.setRolId(rol);
            usuario.setAreaTrabajoId(areaTrabajo);
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
    private void createAreas() {
        for (AreasTrabajo are : AreasTrabajo.values()) {
            AreaTrabajoDTO nuevoArea = new AreaTrabajoDTO();
            nuevoArea.setNombreAreaTrabajo(are.getCodigo());
            nuevoArea.setDescripcion(are.name());
            areaTrabajoService.create(nuevoArea);
        }
    }

    void bucarRol() {
        final String codigo = "ROLE_GESTOR";
        Optional<RolDTO> rolBuscado = rolService.findByCodigo(codigo);

        if (rolBuscado.isEmpty()) {
            rolDTO = new RolDTO();
            rolDTO.setCodigo(codigo);
            rolDTO.setDescripcion("ROL_GESTOR");
            rolDTO = rolService.create(rolDTO);

        } else {
            rolDTO = rolBuscado.get();
        }

//        createRoles();

    }
    void bucarAreaTrabajo() {
        final String nombre = "_RRHH";
        Optional<AreaTrabajoDTO> areaBuscada = areaTrabajoService.findByNombreAreaTrabajo(nombre);
        if (areaBuscada.isEmpty()) {
            areaTrabajoDTO = new AreaTrabajoDTO();
            areaTrabajoDTO.setNombreAreaTrabajo(nombre);
            areaTrabajoDTO.setDescripcion("AREA_TRABAJO_GERENTE_RECURSOS_HUMANOS");
            areaTrabajoDTO = areaTrabajoService.create(areaTrabajoDTO);

        } else {
            areaTrabajoDTO = areaBuscada.get();
        }

//        createAreas();

    }
}
