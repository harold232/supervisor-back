package com.fisiunmsm.grupo3.comp.infraestructure.mapper;

import com.fisiunmsm.grupo3.comp.domain.model.Departamento;
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
@Table("departamento")
public class DepartamentoTable {
    @Id
    private Integer id;
    private String codigo;
    private String nombre;
    private String institucionid;
    private String estado;
    private String departamentoid;

    public static DepartamentoTable fromDomainModel(Departamento departamento) {
        return new DepartamentoTable(departamento.getId(), departamento.getCodigo(), departamento.getNombre(), departamento.getInstitucionid(), departamento.getEstado(), departamento.getDepartamentoid());
    }

    public Departamento toDomainModel() {
        return new Departamento(id, codigo, nombre, institucionid, estado, departamentoid);
    }

    public Mono<Departamento> toMono() {
        return Mono.just(toDomainModel());
    }
}
