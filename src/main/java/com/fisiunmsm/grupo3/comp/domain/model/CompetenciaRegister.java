package com.fisiunmsm.grupo3.comp.domain.model;

import lombok.Builder;

@Builder
public record CompetenciaRegister(
        String codigo,
        String nombre,
        String descripcion,
        Integer planid,
        Integer institucionid,
        Integer departamentoid
) {
}
