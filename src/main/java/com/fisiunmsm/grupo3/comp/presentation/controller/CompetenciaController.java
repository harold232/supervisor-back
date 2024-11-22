package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.CompetenciaService;
import com.fisiunmsm.grupo3.comp.domain.model.Competencia;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaRegister;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaResponse;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaResumen;
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

    @GetMapping("/{id}")
    public Mono<CompetenciaResponse> obtenerCompetenciaPorId(@PathVariable Integer id) {
        return competenciaService.obtenerCompetenciaPorId(id);
    }

    @PutMapping("/{id}")
    public Mono<Competencia> actualizarCompetencia(@PathVariable Integer id, @RequestBody CompetenciaRegister competenciaRegister) {
        return competenciaService.actualizarCompetencia(id, competenciaRegister);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarCompetencia(@PathVariable Integer id) {
        return competenciaService.eliminarCompetencia(id);
    }

    @GetMapping("/all")
    public Flux<CompetenciaResumen> obtenerCompetenciasResumidas() {
        return competenciaService.obtenerCompetencias();
    }

    @GetMapping("/count-generales")
    public Mono<Long> contarCompetenciasGenerales() {
        return competenciaService.contarCompetenciasGenerales();
    }

    @GetMapping("/count-especificas")
    public Mono<Long> contarCompetenciasEspecificas() {
        return competenciaService.contarCompetenciasEspecificas();
    }
}