package com.masmovil.iam.controller;

import com.masmovil.iam.model.Brand;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller("/iam/v1/brands")
@Slf4j
public class BrandsController {

    private static HashMap<String,Brand> brands=new HashMap<>();

    @PostConstruct
    void initialize() {
        this.brands.put("MM",Brand.builder().id("MM")
                .name("MasMovil").build());
        this.brands.put("Y",Brand.builder().id("Y")
                .name("Yoigo").build());
        this.brands.put("PP",Brand.builder().id("PP")
                .name("Pepephone").build());
        this.brands.put("LY",Brand.builder().id("LY")
                .name("Llamaya").build());
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public Brand getBrand(String id) {
        log.info("obteniendo informacion del brand {}",id);
        return brands.get(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Delete("/{id}")
    public Brand delBrand(String id) {
        log.info("borrando el brand {}",id);
        return brands.remove(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Put("/")
    public Brand updateBrand(@Body Brand brand)
    {
        log.info("actualizando el brand {}",brand);
        brands.put(brand.getId(),brand);
        return brand;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/")
    public Brand addBrand(@Body Brand brand)
    {
        log.info("nuevo brand {}",brand);
        brand.setId(UUID.randomUUID().toString());
        brands.put(brand.getId(),brand);
        return brand;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/")
    public List<Brand> getAllBrands()
    {
        log.info("lista brands {}",brands);
        return new ArrayList<Brand>(brands.values());
    }
}
