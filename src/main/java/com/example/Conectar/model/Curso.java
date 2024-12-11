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
@Table("curso")
public class Curso {
    private Long id;
    private String codigo;
    private String nombre;
    private String tipo;
    private Long numHorasTeoria;
    private Long numHorasPractica;
    private Long numHorasLaboratorio;
    private Long numHorasCampo;
    private Long numCreditos;
    private String ciclo;
    private Long estado;
    private Long periodoAcademicoId;
    private Long planEstudiosId;
    private Long institucionid;
    private Long departamentoid;
    private String sumilla;
    private String modalidad;
    private String etiquetas;
}
