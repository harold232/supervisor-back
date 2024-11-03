package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.Departamento;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.DepartamentoTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Flux<Departamento> obtenerDepartamentos() {
        return departamentoRepository.findAll()
                .map(DepartamentoTable::toDomainModel);
    }
}
