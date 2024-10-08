package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.Builder;

@Builder
public record CompetenciaGeneralResponse (
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        String tipo,
        String nivel
) {
}
