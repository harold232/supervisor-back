package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.Competencia;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaRegister;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaResponse;
import com.fisiunmsm.grupo3.comp.domain.model.Institucion;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaTable;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.DepartamentoTable;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.InstitucionTable;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.PlanEstudiosTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.CompetenciaRepository;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.DepartamentoRepository;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.InstitucionRepository;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.PlanEstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private PlanEstudiosRepository planEstudiosRepository;
    @Autowired
    private InstitucionRepository institucionRepository;

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
                .filter(competencia -> "G".equals(competencia.getTipo()))
                .flatMap(this::mapToCompetenciaResponse);
    }

    public Flux<CompetenciaResponse> obtenerCompetenciasEspecificas() {
        return competenciaRepository.findAll()
                .filter(competencia -> "E".equals(competencia.getTipo()))
                .flatMap(this::mapToCompetenciaResponse);
    }

    private Mono<CompetenciaResponse> mapToCompetenciaResponse(CompetenciaTable competencia) {
        Mono<String> departamentoNombre = departamentoRepository.findById(competencia.getDepartamentoid())
                .map(DepartamentoTable::getNombre)
                .defaultIfEmpty("Dept. sin asignar");

        Mono<String> planNombre = planEstudiosRepository.findById(competencia.getPlanid())
                .map(PlanEstudiosTable::getDescripcion)
                .defaultIfEmpty("Plan sin aisgnar");

        Mono<String> institucionNombre = institucionRepository.findById(competencia.getInstitucionid())
                .map(InstitucionTable::getNombreCorto)
                .defaultIfEmpty("Institución sin asignar");


        return Mono.zip(departamentoNombre, planNombre, institucionNombre)
                .map(tuple -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        tuple.getT2(),
                        competencia.getInstitucionid(),
                        tuple.getT3(),
                        competencia.getDepartamentoid(),
                        tuple.getT1(),
                        competencia.getTipo()
                ));
    }

    public Mono<Competencia> actualizarCompetencia(Integer id, CompetenciaRegister competenciaRegister) {
        return competenciaRepository.findById(id)
                .flatMap(competencia -> {
                    competencia.setCodigo(competenciaRegister.codigo());
                    competencia.setNombre(competenciaRegister.nombre());
                    competencia.setDescripcion(competenciaRegister.descripcion());
                    competencia.setPlanid(competenciaRegister.planid());
                    competencia.setInstitucionid(competenciaRegister.institucionid());
                    competencia.setDepartamentoid(competenciaRegister.departamentoid());
                    return competenciaRepository.save(competencia);
                })
                .map(CompetenciaTable::toDomainModel)
                .switchIfEmpty(Mono.error(new RuntimeException("Competencia no encontrada")));
    }

    public Mono<Void> eliminarCompetencia(Integer id) {
        return competenciaRepository.findById(id)
                .flatMap(competencia -> competenciaRepository.delete(competencia));
                //.then()
                //.onErrorResume(e -> Mono.empty());
    }
/*
    private Mono<CompetenciaResponse> mapToCompetenciaResponse(CompetenciaTable competencia) {
        return departamentoRepository.findById(competencia.getDepartamentoid())
                .map(departamento -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        competencia.getInstitucionid(),
                        competencia.getDepartamentoid(),
                        departamento.getNombre(),
                        competencia.getTipo()
                ));
    }*/
/*
    public Flux<CompetenciaResponse> obtenerCompetenciasGenerales() {
        return competenciaRepository.findAll()
                .filter(competencia -> "G".equals(competencia.getTipo())) // Filter for general competencies
                .map(CompetenciaTable::toDomainModel)
                .map(competencia -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        competencia.getInstitucionid(),
                        competencia.getDepartamentoid(),
                        competencia.getTipo()
                ));
    }

    public Flux<CompetenciaResponse> obtenerCompetenciasEspecificas() {
        return competenciaRepository.findAll()
                .filter(competencia -> "E".equals(competencia.getTipo()))
                .map(CompetenciaTable::toDomainModel)
                .map(competencia -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        competencia.getInstitucionid(),
                        competencia.getDepartamentoid(),
                        competencia.getTipo()
                ));
    }*/
}
