package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaEspecifica;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaGeneralResponse;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaEspecificaTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.CompetenciaEspecificaRepository;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.CompetenciaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaGeneral;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaGeneralTable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaGeneralRepository competenciaGRepository;
    @Autowired
    private CompetenciaEspecificaRepository competenciaERepository;

    public Mono<CompetenciaGeneral> crearCompetenciaGeneral(CompetenciaGeneral competenciaG) {
        CompetenciaGeneralTable competenciaEntity = CompetenciaGeneralTable.fromDomainModel(competenciaG);
        return competenciaGRepository.save(competenciaEntity).map(CompetenciaGeneralTable::toDomainModel);
    }

    public Mono<CompetenciaEspecifica> crearCompetenciaEspecifica(CompetenciaEspecifica competenciaE) {
        CompetenciaEspecificaTable competenciaEntity = CompetenciaEspecificaTable.fromDomainModel(competenciaE);
        return competenciaERepository.save(competenciaEntity).map(CompetenciaEspecificaTable::toDomainModel);
    }

    public Flux<CompetenciaEspecifica> obtenerCompetenciasEspecificasPorGeneralId(Long competenciaGeneralId) {
        return competenciaERepository.findByCompetenciaGeneralId(competenciaGeneralId)
                .map(CompetenciaEspecificaTable::toDomainModel);
    }

    public Flux<CompetenciaGeneralResponse> obtenerCompetenciasGenerales() {
        return competenciaGRepository.findAll()
                .map(CompetenciaGeneralTable::toDomainModel)
                .map(competencia -> new CompetenciaGeneralResponse(competencia.getId(), competencia.getCodigo(), competencia.getNombre(), competencia.getDescripcion(), competencia.getTipo(), competencia.getNivel()));
    }

    public Flux<CompetenciaEspecifica> obtenerCompetenciasEspecificas() {
        return competenciaERepository.findAll()
                .map(CompetenciaEspecificaTable::toDomainModel);
    }
}
