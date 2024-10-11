package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fisiunmsm.grupo3.comp.domain.model.InstitucionEducativa;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/institucion")
@CrossOrigin
public class InstitucionController {

    @Autowired
    private InstitucionService institucionService;

    @PostMapping("/institucion-educativa")
    public Mono<InstitucionEducativa> nuevaInstitucionE(@RequestBody InstitucionEducativa institucion) {
        return institucionService.crearInstitucionEducativa(institucion);
    }

    @GetMapping("/instituciones-educativas")
    public Flux<InstitucionEducativa> obtenerInstitucionesEducativas() {
        return institucionService.obtenerInstitucionesEducativas();
    }

}