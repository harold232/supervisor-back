package com.example.Conectar.repository;

import com.example.Conectar.model.PlanEstudios;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PlanEstudiosRepository extends R2dbcRepository<PlanEstudios, Integer> {
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
            @Param("carreraid") int carreraid
    );
}
