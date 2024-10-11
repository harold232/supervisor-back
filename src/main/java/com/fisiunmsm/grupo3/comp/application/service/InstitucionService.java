package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.InstitucionEducativa;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.InstitucionEducativaTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.InstitucionEducativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InstitucionService {

    @Autowired
    private InstitucionEducativaRepository institucionRepository;

    public Mono<InstitucionEducativa> crearInstitucionEducativa(InstitucionEducativa institucionE) {
        InstitucionEducativaTable institucionEntity = InstitucionEducativaTable.fromDomainModel(institucionE);
        return institucionRepository.save(institucionEntity).map(InstitucionEducativaTable::toDomainModel);
    }

    public Flux<InstitucionEducativa> obtenerInstitucionesEducativasPorId(Long InstitucionEducativaId) {
        return institucionRepository.findByInstitucionEducativaId(InstitucionEducativaId)
                .map(InstitucionEducativaTable::toDomainModel);
    }

    public Flux<InstitucionEducativa> obtenerInstitucionesEducativas() {
        return institucionRepository.findAll()
                .map(InstitucionEducativaTable::toDomainModel);
    }

}
