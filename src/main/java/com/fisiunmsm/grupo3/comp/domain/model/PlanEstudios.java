package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanEstudios {
    private Integer id;
    private String codigo;
    private String descripcion;
    private String vigencia;
    private Integer institucionid;
    private Integer departamentoid;
    private String estado;
    private Integer carreraid;
}
