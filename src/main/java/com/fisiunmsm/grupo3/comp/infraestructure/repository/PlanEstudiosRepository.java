package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import com.fisiunmsm.grupo3.comp.infraestructure.mapper.PlanEstudiosTable;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;

public interface PlanEstudiosRepository extends R2dbcRepository<PlanEstudiosTable, Integer> {

    @Query("INSERT INTO planestudios" +
            " (codigo, descripcion, vigencia, institucionid, departamentoid, estado, carreraid)" +
            " VALUES (:codigo, :descripcion, :vigencia, :institucionid, :departamentoid, :estado, :carreraid)")
    Mono<Void> insertarPlanEstudios(
            @Param("codigo") String codigo,
            @Param("descripcion") String descripcion,
            @Param("vigencia") String vigencia,
            @Param("institucionid") int institucionid,
            @Param("departamentoid") int departamentoid,
            @Param("estado") String estado,
            @Param("carreraid") int carreraid);
}
