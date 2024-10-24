package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.CompetenciaService;
import com.fisiunmsm.grupo3.comp.application.service.InstitucionService;
import com.fisiunmsm.grupo3.comp.domain.model.Competencia;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaRegister;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaResponse;
import com.fisiunmsm.grupo3.comp.domain.model.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/competencia")
@CrossOrigin
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @PostMapping("/general")
    public Mono<Competencia> nuevaCompetenciaG(@RequestBody CompetenciaRegister competenciaRegister) {
        return competenciaService.crearCompetenciaGeneral(competenciaRegister);
    }

    @PostMapping("/especifica")
    public Mono<Competencia> nuevaCompetenciaE(@RequestBody CompetenciaRegister competenciaRegister) {
        return competenciaService.crearCompetenciaEspecifica(competenciaRegister);
    }

    @GetMapping("/competencias-generales")
    public Flux<CompetenciaResponse> obtenerCompetenciasGenerales() {
        return competenciaService.obtenerCompetenciasGenerales();
    }

    @GetMapping("/competencias-especificas")
    public Flux<CompetenciaResponse> obtenerCompetenciasEspecificas() {
        return competenciaService.obtenerCompetenciasEspecificas();
    }
}