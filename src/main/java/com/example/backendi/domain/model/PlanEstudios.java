package com.example.backendi.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PlanEstudios {
    @Id
    private Long id;
    private String codigo;
    private String descripcion;
    private String vigencia;
    private Long institucionId;
    private Long departamentoId;
    private Long carreraId;
    private String estado;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getVigencia() { return vigencia; }
    public void setVigencia(String vigencia) { this.vigencia = vigencia; }

    public Long getInstitucionId() { return institucionId; }
    public void setInstitucionId(Long institucionId) { this.institucionId = institucionId; }

    public Long getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(Long departamentoId) { this.departamentoId = departamentoId; }

    public Long getCarreraId() { return carreraId; }
    public void setCarreraId(Long carreraId) { this.carreraId = carreraId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}




