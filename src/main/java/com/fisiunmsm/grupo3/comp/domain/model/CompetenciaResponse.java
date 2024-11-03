package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.Builder;

@Builder
public record CompetenciaResponse(
        Integer id,
        String codigo,
        String nombre,
        String descripcion,
        Integer planId,
        String planNombre,
        Integer institucionId,
        String institucionNombre,
        Integer departamentoId,
        String departamentoNombre,
        String tipo
) {
}