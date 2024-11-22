package com.fisiunmsm.grupo3.comp.infraestructure.mapper;

import com.fisiunmsm.grupo3.comp.domain.model.Institucion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("institucion")
public class InstitucionTable {
    @Id
    private Integer id;
    private String codigo;
    private String nombreCorto;
    private String nombreLargo;
    private String nombreComercial;
    private String estado;

    public static InstitucionTable fromDomainModel(Institucion institucion) {
        return new InstitucionTable(institucion.getId(), institucion.getCodigo(), institucion.getNombreCorto(), institucion.getNombreLargo(), institucion.getNombreComercial(), institucion.getEstado());
    }

    public Institucion toDomainModel() {
        return new Institucion(id, codigo, nombreCorto, nombreLargo, nombreComercial, estado);
    }

    public Mono<Institucion> toMono() {
        return Mono.just(toDomainModel());
    }
}
