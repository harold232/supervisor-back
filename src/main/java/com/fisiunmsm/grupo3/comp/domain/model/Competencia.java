package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competencia {
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Integer planid;
    private Integer institucionid;
    private Integer departamentoid;
    private String tipo;
}
