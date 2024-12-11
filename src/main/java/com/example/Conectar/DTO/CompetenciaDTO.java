package com.example.Conectar.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CompetenciaDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Long planid;
    private Long institucionid;
    private Long departamentoid;
    private String tipo;
}
