package com.fisiunmsm.grupo3.comp.infraestructure.mapper;

import com.fisiunmsm.grupo3.comp.domain.model.PlanEstudios;
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
@Table("planestudios")
public class PlanEstudiosTable {
    @Id
    private Integer id;
    private String codigo;
    private String descripcion;
    private String vigencia;
    private Integer institucionid;
    private Integer departamentoid;
    private String estado;
    private Integer carreraid;

    public static PlanEstudiosTable fromDomainModel(PlanEstudios planEstudios) {
        return new PlanEstudiosTable(planEstudios.getId(), planEstudios.getCodigo(), planEstudios.getDescripcion(), planEstudios.getVigencia(), planEstudios.getInstitucionid(), planEstudios.getDepartamentoid(), planEstudios.getEstado(), planEstudios.getCarreraid());
    }

    public PlanEstudios toDomainModel() {
        return new PlanEstudios(id, codigo, descripcion, vigencia, institucionid, departamentoid, estado, carreraid);
    }

    public Mono<PlanEstudios> toMono() {
        return Mono.just(toDomainModel());
    }
}
