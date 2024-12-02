package com.fisiunmsm.grupo3.comp.infraestructure.repository;

import com.fisiunmsm.grupo3.comp.domain.model.CompetenciasPorCursoDTO;
import com.fisiunmsm.grupo3.comp.domain.model.EstadisticasDTO;
import com.fisiunmsm.grupo3.comp.domain.model.PromedioCreditosHorasDTO;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface CompetenciaRepository extends R2dbcRepository<CompetenciaTable, Integer> {

    @Query("SELECT * FROM competencia WHERE (:tipo IS NULL OR tipo = :tipo) " +
            "AND (:departamento IS NULL OR departamentoid = :departamento) " +
            "AND (:institucion IS NULL OR institucionid = :institucion)")
    Flux<CompetenciaTable> buscarCompetencias(String tipo, Integer departamento, Integer institucion);

    @Query("SELECT tipo, COUNT(*) AS total FROM competencia GROUP BY tipo")
    Flux<EstadisticasDTO> obtenerEstadisticas();

    @Query("""
        SELECT c.codigo AS codigo_curso, c.nombre AS nombre_curso, co.tipo AS tipo_competencia,
               COUNT(co.id) AS total_competencias
        FROM cursocompetencia cc
        JOIN competencia co ON cc.competenciaid = co.id
        JOIN curso c ON cc.cursoid = c.id
        GROUP BY c.codigo, c.nombre, co.tipo
        ORDER BY c.codigo, co.tipo
    """)
    Flux<CompetenciasPorCursoDTO> obtenerCompetenciasPorCursoYTipo();

    @Query("""
        SELECT co.codigo AS codigo_competencia, co.nombre AS nombre_competencia,
               AVG(c.numCreditos) AS promedio_creditos,
               AVG(c.numHorasTeoria + c.numHorasPractica + c.numHorasLaboratorio) AS promedio_horas
        FROM cursocompetencia cc
        JOIN curso c ON cc.cursoid = c.id
        JOIN competencia co ON cc.competenciaid = co.id
        GROUP BY co.codigo, co.nombre
        ORDER BY co.codigo
    """)
    Flux<PromedioCreditosHorasDTO> obtenerPromedioCreditosYHoras();

}
