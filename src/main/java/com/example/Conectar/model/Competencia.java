package com.example.Conectar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("competencia")
public class Competencia {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Long planid;
    private Long institucionid;
    private Long departamentoid;
    private String tipo;
}