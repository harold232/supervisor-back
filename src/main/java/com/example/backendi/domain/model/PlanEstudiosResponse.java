package com.example.backendi.domain.model;
import lombok.Builder;

public record PlanEstudiosResponse(
        Long id,
        String codigo,
        String descripcion,
        String vigencia,
        int institucionId,
        int departamentoId,
        String estado,
        int carreraId
) {
}
