package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.CompetenciaService;
import com.fisiunmsm.grupo3.comp.application.service.ReporteService;
import com.fisiunmsm.grupo3.comp.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/competencia")
@CrossOrigin
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;
    @Autowired
    private ReporteService reporteService;

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

    @GetMapping("/buscar")
    public Flux<CompetenciaResponse> buscarCompetencias(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Integer departamentoId,
            @RequestParam(required = false) Integer institucionId,
            @RequestParam(required = false) Integer planId) {
        return competenciaService.buscarCompetencias(tipo, departamentoId, institucionId, planId);
    }

    @PostMapping("/importar-csv")
    public Mono<Void> importarCompetenciasCsv(@RequestParam("file") MultipartFile file) {
        return competenciaService.importarCompetenciasCsv(file);
    }

    @PostMapping("/importar-excel")
    public Mono<Void> importarCompetenciasExcel(@RequestParam("file") MultipartFile file) {
        return competenciaService.importarCompetenciasExcel(file);
    }

    @GetMapping("/estadisticas")
    public Flux<EstadisticasDTO> obtenerEstadisticas() {
        return competenciaService.obtenerEstadisticas();
    }

    @GetMapping("/estadisticas/competencias-por-curso")
    public Flux<CompetenciasPorCursoDTO> obtenerCompetenciasPorCursoYTipo() {
        return competenciaService.obtenerCompetenciasPorCursoYTipo();
    }

    @GetMapping("/estadisticas/promedio-creditos-horas")
    public Flux<PromedioCreditosHorasDTO> obtenerPromedioCreditosYHoras() {
        return competenciaService.obtenerPromedioCreditosYHoras();
    }
}