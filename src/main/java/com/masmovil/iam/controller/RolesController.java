package com.masmovil.iam.controller;

import com.masmovil.iam.model.Role;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller("/iam/v1/roles")
@Slf4j
public class RolesController {

    private HashMap<String,Role> roles=new HashMap<>();

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public Role getRole(String id) {
        log.info("obteniendo informacion del role {}",id);
        return roles.get(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Delete("/{id}")
    public Role delRole(String id) {
        log.info("borrando el role {}",id);
        return roles.remove(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Put("/")
    public Role updateRole(@Body Role role)
    {
        log.info("actualizando el role {}",role);
        roles.put(role.getId(),role);
        return role;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/")
    public Role addRole(@Body Role role)
    {
        log.info("nuevo role {}",role);
        role.setId(UUID.randomUUID().toString());
        roles.put(role.getId(),role);
        return role;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/")
    public List<Role> getAllRoles()
    {
        log.info("lista roles {}",roles);
        return new ArrayList<Role>(roles.values());
    }
}
