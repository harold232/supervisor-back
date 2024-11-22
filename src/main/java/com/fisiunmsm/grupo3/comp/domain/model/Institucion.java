package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Institucion {
    private Integer id;
    private String codigo;
    private String nombreCorto;
    private String nombreLargo;
    private String nombreComercial;
    private String estado;
}
