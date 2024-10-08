package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompetenciaEspecifica {

    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String nivel;

    private Long competenciaGeneralId;
}
