package com.example.Conectar.repository;

import com.example.Conectar.model.Curso;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CursoRepository extends R2dbcRepository<Curso, Integer> {

    @Query("INSERT INTO curso (codigo, nombre,tipo,numHorasTeoria,numHorasPractica,numHorasLaboratorio, numHorasCampo, " +
            "numCreditos, ciclo, estado, periodoacademicoid, planEstudiosId, institucionid, departamentoid, " +
            "sumilla, modalidad, etiquetas) " +
            "VALUES (:codigo, :nombre, :tipo, :numHorasTeoria, :numHorasPractica, :numHorasLaboratorio, :numHorasCampo, " +
            ":numCreditos, :ciclo, :estado, :periodoacademicoid, :planestudiosid, :institucionid, :departamentoid, " +
            ":sumilla, :modalidad, :etiquetas) ")
    Mono<Void> insertarCurso(
            @Param("codigo") String codigo, @Param("nombre") String nombre, @Param("tipo") String tipo,
            @Param("numHorasTeoria") int numHorasTeoria, @Param("numHorasPractica") int numHorasPractica,
            @Param("numHorasLaboratorio") int numHorasLaboratorio, @Param("numHorasCampo") Integer numHorasCampo,
            @Param("numCreditos") double numCreditos, @Param("ciclo") String ciclo,
            @Param("estado") int estado, @Param("periodoacademicoid") int periodoacademicoid,
            @Param("planestudiosid") int planestudiosid, @Param("institucionid") int institucionid,
            @Param("departamentoid") int departamentoid, @Param("sumilla") String sumilla,
            @Param("modalidad") String modalidad, @Param("etiquetas") String etiquetas
    );
}

