package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import com.fisiunmsm.grupo3.comp.infraestructure.mapper.InstitucionTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface InstitucionRepository extends R2dbcRepository<InstitucionTable, Integer> {
}
