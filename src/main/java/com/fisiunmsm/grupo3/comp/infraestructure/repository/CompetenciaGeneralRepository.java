package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaGeneralTable;

public interface CompetenciaGeneralRepository extends R2dbcRepository<CompetenciaGeneralTable, Long> {
    
}
