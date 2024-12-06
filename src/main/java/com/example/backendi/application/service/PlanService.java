package com.example.backendi.application.service;

import com.example.backendi.domain.model.PlanEstudios;
import com.example.backendi.infraestructure.repository.PlanRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    // Guardar un nuevo plan
    public Mono<PlanEstudios> savePlan(PlanEstudios plan) {
        if (plan.getInstitucionId() == null) {
            plan.setInstitucionId(-1L); // Valor por defecto
        }
        if (plan.getDepartamentoId() == null) {
            plan.setDepartamentoId(-1L); // Valor por defecto
        }
        if (plan.getCarreraId() == null) {
            plan.setCarreraId(-1L); // Valor por defecto
        }
        if (plan.getEstado() == null) {
            plan.setEstado("activo"); // Valor predeterminado
        }

        return planRepository.save(plan);
    }

    // Actualizar un plan existente
    public Mono<PlanEstudios> updatePlan(Long id, PlanEstudios planDetails) {
        return planRepository.findById(id)
                .flatMap(existingPlan -> {
                    if (planDetails.getCodigo() != null) {
                        existingPlan.setCodigo(planDetails.getCodigo());
                    }
                    if (planDetails.getDescripcion() != null) {
                        existingPlan.setDescripcion(planDetails.getDescripcion());
                    }
                    if (planDetails.getVigencia() != null) {
                        existingPlan.setVigencia(planDetails.getVigencia());
                    }
                    if (planDetails.getInstitucionId() != null) {
                        existingPlan.setInstitucionId(planDetails.getInstitucionId());
                    }
                    if (planDetails.getDepartamentoId() != null) {
                        existingPlan.setDepartamentoId(planDetails.getDepartamentoId());
                    }
                    if (planDetails.getCarreraId() != null) {
                        existingPlan.setCarreraId(planDetails.getCarreraId());
                    }
                    if (planDetails.getEstado() != null) {
                        existingPlan.setEstado(planDetails.getEstado());
                    }

                    return planRepository.save(existingPlan);
                });
    }

    // Eliminar un plan por ID
    public Mono<Void> deletePlan(Long id) {
        return planRepository.findById(id)
                .flatMap(existingPlan -> planRepository.delete(existingPlan));
    }

    // Obtener un plan por ID
    public Mono<PlanEstudios> getPlanById(Long id) {
        return planRepository.findById(id);
    }

    // Obtener todos los planes
    public Flux<PlanEstudios> getAllPlans() {
        return planRepository.findAll();
    }

    // Buscar un plan por código
    public Mono<PlanEstudios> getPlanByCodigo(String codigo) {
        return planRepository.findByCodigo(codigo);
    }
}






