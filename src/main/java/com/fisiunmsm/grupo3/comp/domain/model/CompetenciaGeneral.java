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
public class CompetenciaGeneral {

    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String nivel;

    //private PlanEstudios planEstudios;

}