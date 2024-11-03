package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import com.fisiunmsm.grupo3.comp.infraestructure.mapper.PlanEstudiosTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PlanEstudiosRepository extends R2dbcRepository<PlanEstudiosTable, Integer> {

}
