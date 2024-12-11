package com.example.Conectar.DTO;

import lombok.*;

@Data
@Builder
public class CursoDTO {
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