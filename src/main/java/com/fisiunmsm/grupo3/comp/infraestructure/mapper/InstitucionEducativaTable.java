package com.fisiunmsm.grupo3.comp.infraestructure.mapper;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fisiunmsm.grupo3.comp.domain.model.InstitucionEducativa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table("institucion")
public class InstitucionEducativaTable {
    
    @Id
    private Long id;
    private String codigo;
    private String nombreCorto;
    private String nombreLargo;
    private String nombreComercial;
    private String estado;

    public static InstitucionEducativaTable fromDomainModel(InstitucionEducativa institucion) {
        return new InstitucionEducativaTable(institucion.getId(), institucion.getCodigo(), institucion.getNombreCorto(), institucion.getNombreLargo(), institucion.getNombreComercial(), institucion.getEstado());
    }

    public InstitucionEducativa toDomainModel() {
        return new InstitucionEducativa(id, codigo, nombreCorto, nombreLargo, nombreComercial, estado);
    }

    public Mono<InstitucionEducativa> toMono() {
        return Mono.just(toDomainModel());
    }
}
