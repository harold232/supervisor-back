package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.DepartamentoService;
import com.fisiunmsm.grupo3.comp.domain.model.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/departamento")
@CrossOrigin
public class DepartamentoController {
    @Autowired
    public DepartamentoService departamentoService;

    @GetMapping("/all")
    public Flux<Departamento> obtenerDepartamentos() {
        return departamentoService.obtenerDepartamentos();
    }
}
