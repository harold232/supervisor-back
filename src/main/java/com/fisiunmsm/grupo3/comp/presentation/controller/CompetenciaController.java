package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.CompetenciaService;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaEspecifica;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaGeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaGeneral;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/competencia")
@CrossOrigin
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @PostMapping("/general")
    public Mono<CompetenciaGeneral> nuevaCompetenciaG(@RequestBody CompetenciaGeneral competenciaG) {
        return competenciaService.crearCompetenciaGeneral(competenciaG);
    }

    @PostMapping("/especifica")
    public Mono<CompetenciaEspecifica> nuevaCompetenciaE(@RequestBody CompetenciaEspecifica competenciaE) {
        return competenciaService.crearCompetenciaEspecifica(competenciaE);
    }

    @GetMapping("/competencias-generales")
    public Flux<CompetenciaGeneralResponse> obtenerCompetenciasGenerales() {
        return competenciaService.obtenerCompetenciasGenerales();
    }

    @GetMapping("/competencias-especificas")
    public Flux<CompetenciaEspecifica> obtenerCompetenciasEspecificas() {
        return competenciaService.obtenerCompetenciasEspecificas();
    }

}