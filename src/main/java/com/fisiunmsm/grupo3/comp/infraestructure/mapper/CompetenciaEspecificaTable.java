package com.fisiunmsm.grupo3.comp.infraestructure.mapper;

import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaEspecifica;
import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaGeneral;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table("competencias_especificas")
public class CompetenciaEspecificaTable {
    @Id
    private Long id;

    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String nivel;
    private Long competenciaGeneralId;

    public static CompetenciaEspecificaTable fromDomainModel(CompetenciaEspecifica competencia) {
        return new CompetenciaEspecificaTable(competencia.getId(), competencia.getCodigo(), competencia.getNombre(), competencia.getDescripcion(), competencia.getTipo(), competencia.getNivel(), competencia.getCompetenciaGeneralId());
    }

    public CompetenciaEspecifica toDomainModel() {
        return new CompetenciaEspecifica(id, codigo, nombre, descripcion, tipo, nivel, competenciaGeneralId);
    }

    public Mono<CompetenciaEspecifica> toMono() {
        return Mono.just(toDomainModel());
    }
}
