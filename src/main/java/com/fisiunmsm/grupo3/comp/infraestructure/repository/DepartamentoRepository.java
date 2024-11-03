package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import com.fisiunmsm.grupo3.comp.infraestructure.mapper.DepartamentoTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface DepartamentoRepository extends R2dbcRepository<DepartamentoTable, Integer> {
}
