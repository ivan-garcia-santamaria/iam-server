package com.masmovil.iam.controller;

import com.masmovil.iam.model.Profile;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller("/iam/v1/profiles")
@Slf4j
public class ProfilesController {

    private HashMap<String,Profile> profiles=new HashMap<>();

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public Profile getProfile(String id) {
        log.info("obteniendo informacion del perfil {}",id);
        return profiles.get(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Delete("/{id}")
    public Profile delProfile(String id) {
        log.info("borrando el perfil {}",id);
        return profiles.remove(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Put("/")
    public Profile updateProfile(@Body Profile profile)
    {
        log.info("actualizando el perfil {}",profile);
        profiles.put(profile.getId(),profile);
        return profile;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/")
    public Profile addProfile(@Body Profile profile)
    {
        log.info("nuevo perfil {}",profile);
        profile.setId(UUID.randomUUID().toString());
        profiles.put(profile.getId(),profile);
        return profile;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/")
    public List<Profile> getAllProfiles()
    {
        log.info("lista perfils {}",profiles);
        return new ArrayList<Profile>(profiles.values());
    }
}
