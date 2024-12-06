package com.example.backendi.presentation.controller;

import com.example.backendi.domain.model.PlanEstudios;
import com.example.backendi.application.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/plan")
@CrossOrigin(origins = "*")  // Permite solicitudes desde el puerto 5173
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    // Crear nuevo plan
    @PostMapping
    public Mono<ResponseEntity<PlanEstudios>> createPlan(@RequestBody PlanEstudios plan) {
        return planService.savePlan(plan)
                .map(savedPlan -> new ResponseEntity<>(savedPlan, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    // Actualizar un plan
    @PutMapping("/{id}")
    public Mono<ResponseEntity<PlanEstudios>> updatePlan(@PathVariable("id") Long id, @RequestBody PlanEstudios plan) {
        return planService.updatePlan(id, plan)
                .map(updatedPlan -> new ResponseEntity<>(updatedPlan, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un plan por ID
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePlan(@PathVariable("id") Long id) {
        return planService.deletePlan(id)
                .map(deleted -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener un plan por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<PlanEstudios>> getPlanById(@PathVariable("id") Long id) {
        return planService.getPlanById(id)
                .map(plan -> new ResponseEntity<>(plan, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener todos los planes
    @GetMapping
    public Flux<ResponseEntity<PlanEstudios>> getAllPlans() {
        return planService.getAllPlans()
                .map(plan -> new ResponseEntity<>(plan, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    // Obtener un plan por código
    @GetMapping("/codigo/{codigo}")
    public Mono<ResponseEntity<PlanEstudios>> getPlanByCodigo(@PathVariable("codigo") String codigo) {
        return planService.getPlanByCodigo(codigo)
                .map(plan -> new ResponseEntity<>(plan, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
