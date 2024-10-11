package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.fisiunmsm.grupo3.comp.infraestructure.mapper.InstitucionEducativaTable;

import reactor.core.publisher.Flux;

public interface InstitucionEducativaRepository extends R2dbcRepository<InstitucionEducativaTable, Long> {
        Flux<InstitucionEducativaTable> findByInstitucionEducativaId(Long InstitucionEducativaId);
}
