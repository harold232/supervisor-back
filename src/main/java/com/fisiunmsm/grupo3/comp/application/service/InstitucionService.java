package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.Institucion;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.InstitucionTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class InstitucionService {

    @Autowired
    private InstitucionRepository institucionRepository;

    public Flux<Institucion> obtenerInstituciones() {
        return institucionRepository.findAll().
                map(InstitucionTable::toDomainModel);
    }
}
