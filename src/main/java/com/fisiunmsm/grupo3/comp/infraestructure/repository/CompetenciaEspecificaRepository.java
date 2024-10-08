package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaEspecificaTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface CompetenciaEspecificaRepository extends R2dbcRepository<CompetenciaEspecificaTable, Long> {
    Flux<CompetenciaEspecificaTable> findByCompetenciaGeneralId(Long competenciaGeneralId);
}
