package com.fisiunmsm.grupo3.comp.infraestructure.mapper;

import com.fisiunmsm.grupo3.comp.domain.model.Competencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("competencia")
public class CompetenciaTable {
    @Id
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Integer planid;
    private Integer institucionid;
    private Integer departamentoid;
    private String tipo;

    public static CompetenciaTable fromDomainModel(Competencia competencia) {
        return new CompetenciaTable(competencia.getId(), competencia.getCodigo(), competencia.getNombre(), competencia.getDescripcion(), competencia.getPlanid(), competencia.getInstitucionid(), competencia.getDepartamentoid(), competencia.getTipo());
    }

    public Competencia toDomainModel() {
        return new Competencia(id, codigo, nombre, descripcion, planid, institucionid, departamentoid, tipo);
    }

    public Mono<Competencia> toMono() {
        return Mono.just(toDomainModel());
    }
}
