package com.fisiunmsm.grupo3.comp.infraestructure.mapper;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaGeneral;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table("competencias_generales")
public class CompetenciaGeneralTable {
    
    @Id
    private Long id;

    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String nivel;

    public static CompetenciaGeneralTable fromDomainModel(CompetenciaGeneral competencia) {
        return new CompetenciaGeneralTable(competencia.getId(), competencia.getCodigo(), competencia.getNombre(), competencia.getDescripcion(), competencia.getTipo(), competencia.getNivel());
    }

    public CompetenciaGeneral toDomainModel() {
        return new CompetenciaGeneral(id, codigo, nombre, descripcion, tipo, nivel);
    }

    public Mono<CompetenciaGeneral> toMono() {
        return Mono.just(toDomainModel());
    }
}
