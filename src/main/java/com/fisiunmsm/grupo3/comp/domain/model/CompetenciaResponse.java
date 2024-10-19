package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.Builder;

@Builder
public record CompetenciaResponse(
        Integer id,
        String codigo,
        String nombre,
        String descripcion,
        String planId,
        String institucionId,
        String deparamentoId,
        String tipo
) {
}