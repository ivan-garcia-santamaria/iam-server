package com.masmovil.iam.controller;

import com.masmovil.iam.model.Group;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller("/iam/v1/groups")
@Slf4j
public class GroupsController {

    private HashMap<String,Group> grupos=new HashMap<>();

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public Group getGroup(String id) {
        log.info("obteniendo informacion del grupo {}",id);
        return grupos.get(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Delete("/{id}")
    public Group delGroup(String id) {
        log.info("borrando el grupo {}",id);
        return grupos.remove(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Put("/")
    public Group updateGroup(@Body Group group)
    {
        log.info("actualizando el grupo {}",group);
        grupos.put(group.getId(),group);
        return group;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/")
    public Group addGroup(@Body Group group)
    {
        log.info("nuevo grupo {}",group);
        group.setId(UUID.randomUUID().toString());
        grupos.put(group.getId(),group);
        return group;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/")
    public List<Group> getAllGroups()
    {
        log.info("lista grupos {}",grupos);
        return new ArrayList<Group>(grupos.values());
    }
}
