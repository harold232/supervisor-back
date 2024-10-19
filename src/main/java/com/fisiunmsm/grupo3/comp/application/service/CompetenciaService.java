package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.Competencia;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaRegister;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaResponse;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    public Mono<Competencia> crearCompetenciaGeneral(CompetenciaRegister competenciaRegister) {
        Competencia competencia = new Competencia(
                null,
                competenciaRegister.codigo(),
                competenciaRegister.nombre(),
                competenciaRegister.descripcion(),
                competenciaRegister.planid(),
                competenciaRegister.institucionid(),
                competenciaRegister.departamentoid(),
                "G"
        );
        CompetenciaTable competenciaEntity = CompetenciaTable.fromDomainModel(competencia);
        return competenciaRepository.save(competenciaEntity)
                .map(CompetenciaTable::toDomainModel);
    }

    public Mono<Competencia> crearCompetenciaEspecifica(CompetenciaRegister competenciaRegister) {
        Competencia competencia = new Competencia(
                null,
                competenciaRegister.codigo(),
                competenciaRegister.nombre(),
                competenciaRegister.descripcion(),
                competenciaRegister.planid(),
                competenciaRegister.institucionid(),
                competenciaRegister.departamentoid(),
                "E"
        );
        CompetenciaTable competenciaEntity = CompetenciaTable.fromDomainModel(competencia);
        return competenciaRepository.save(competenciaEntity).map(CompetenciaTable::toDomainModel);
    }

    public Flux<CompetenciaResponse> obtenerCompetenciasGenerales() {
        return competenciaRepository.findAll()
                .filter(competencia -> "G".equals(competencia.getTipo())) // Filter for general competencies
                .map(CompetenciaTable::toDomainModel)
                .map(competencia -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid().toString(),
                        competencia.getInstitucionid().toString(),
                        competencia.getDepartamentoid().toString(),
                        competencia.getTipo()
                ));
    }

    public Flux<CompetenciaResponse> obtenerCompetenciasEspecificas() {
        return competenciaRepository.findAll()
                .filter(competencia -> "E".equals(competencia.getTipo())) // Filter for general competencies
                .map(CompetenciaTable::toDomainModel)
                .map(competencia -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid().toString(),
                        competencia.getInstitucionid().toString(),
                        competencia.getDepartamentoid().toString(),
                        competencia.getTipo()
                ));
    }
}
