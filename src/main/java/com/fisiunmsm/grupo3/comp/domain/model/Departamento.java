package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    private Integer id;
    private String codigo;
    private String nombre;
    private String institucionid;
    private String estado;
    private String departamentoid;
}
