package com.example.backendi.infraestructure.mapper;

import com.example.backendi.domain.model.PlanEstudios;
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
@Table("plan_estudios")
public class PlanTable {
    @Id
    private Long id;

    private String codigo;
    private String descripcion;
    private String vigencia;

    private Long institucionId;
    private Long departamentoId;
    private Long carreraId;
    private String estado;


    public PlanEstudios toDomainModel() {
        return new PlanEstudios(id, codigo, descripcion, vigencia, institucionId, departamentoId, carreraId, estado);
    }

    public Mono<PlanEstudios> toMono() {
        return Mono.just(toDomainModel());
    }
}
