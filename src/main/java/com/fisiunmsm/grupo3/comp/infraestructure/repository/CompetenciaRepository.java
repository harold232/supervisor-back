package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import com.fisiunmsm.grupo3.comp.domain.model.Competencia;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CompetenciaRepository extends R2dbcRepository<CompetenciaTable, Integer> {

}
