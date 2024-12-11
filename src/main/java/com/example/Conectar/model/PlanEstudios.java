package com.example.Conectar.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("planestudios")
public class PlanEstudios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private String descripcion;
    private String vigencia;
    private Integer institucionid;
    private Integer departamentoid;
    private String estado;
    private Integer carreraid;
}
