package com.example.Conectar.repository;

import com.example.Conectar.model.Competencia;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CompetenciaRepository extends R2dbcRepository<Competencia, Integer> {
    @Query("INSERT INTO competencia (codigo, nombre, descripcion, planid, institucionid, departamentoid, tipo) " +
            "VALUES (:codigo, :nombre, :descripcion, :planid, :institucionid, :departamentoid, :tipo)")
    Mono<Void> insertarCompetencia(String codigo, String nombre, String descripcion, Long planid,
                                   Long institucionid, Long departamentoid, String tipo);
}
