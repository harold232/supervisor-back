package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.PlanEstudios;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.PlanEstudiosTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.PlanEstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PlanEstudiosService {
    @Autowired
    private PlanEstudiosRepository planEstudiosRepository;

    public Flux<PlanEstudios> obtenerPLanesEstudios() {
        return planEstudiosRepository.findAll()
                .map(PlanEstudiosTable::toDomainModel);
    }
}
