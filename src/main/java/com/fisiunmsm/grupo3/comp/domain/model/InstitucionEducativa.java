package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitucionEducativa {

    private Long id;
    private String codigo;
    private String nombreCorto;
    private String nombreLargo;
    private String nombreComercial;
    private String estado;
}
