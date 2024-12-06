package com.fisiunmsm.grupo3.comp.presentation.controller;

import com.fisiunmsm.grupo3.comp.application.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/reporte")
@CrossOrigin
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/pdf")
    public Mono<ResponseEntity<byte[]>> descargarReportePdf() {
        return reporteService.generarReportePdf()
                .map(pdfBytes -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdfBytes));
    }

}