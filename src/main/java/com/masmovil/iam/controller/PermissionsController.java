package com.masmovil.iam.controller;

import com.masmovil.iam.model.Permission;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller("/iam/v1/permissions")
@Slf4j
public class PermissionsController {

    private HashMap<String,Permission> permissions=new HashMap<>();

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public Permission getPermission(String id) {
        log.info("obteniendo informacion del permiso {}",id);
        return permissions.get(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Delete("/{id}")
    public Permission delPermission(String id) {
        log.info("borrando el permiso {}",id);
        return permissions.remove(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Put("/")
    public Permission updatePermission(@Body Permission permission)
    {
        log.info("actualizando el permiso {}",permission);
        permissions.put(permission.getId(),permission);
        return permission;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/")
    public Permission addPermission(@Body Permission permission)
    {
        log.info("nuevo permiso {}",permission);
        permission.setId(UUID.randomUUID().toString());
        permissions.put(permission.getId(),permission);
        return permission;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/")
    public List<Permission> getAllpermissions()
    {
        log.info("lista permisos {}",permissions);
        return new ArrayList<Permission>(permissions.values());
    }
}
