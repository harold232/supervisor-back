package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.PlanEstudiosService;
import com.fisiunmsm.grupo3.comp.domain.model.PlanEstudios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/plan-estudios")
@CrossOrigin
public class PlanEstudiosController {
    @Autowired
    private PlanEstudiosService planEstudiosService;

    @GetMapping("/all")
    public Flux<PlanEstudios> obtenerPlanesEstudios() {
        return planEstudiosService.obtenerPLanesEstudios();
    }
}