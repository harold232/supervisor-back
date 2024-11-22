package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.InstitucionService;
import com.fisiunmsm.grupo3.comp.domain.model.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/institucion")
@CrossOrigin
public class InstitucionController {
    @Autowired
    public InstitucionService institucionService;

    @GetMapping("/all")
    public Flux<Institucion> obtenerInstituciones() {
        return institucionService.obtenerInstituciones();
    }
}
