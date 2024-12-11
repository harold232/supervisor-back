package com.example.Conectar.service;

import com.example.Conectar.DTO.CursoDTO;
import com.example.Conectar.controller.PlanEstudiosController;
import com.example.Conectar.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import com.example.Conectar.repository.PlanEstudiosRepository;
import reactor.core.scheduler.Schedulers;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class PlanEstudiosService {

    private static final Logger logger = LoggerFactory.getLogger(PlanEstudiosController.class);

    private final PlanEstudiosRepository planEstudiosRepository;
    private final CursoService cursoService;
    private final CompetenciaService competenciaService;

    @Transactional
    public Mono<Void> cargarPlanEstudiosDesdeExcel(Mono<FilePart> filePart) {
        return filePart.flatMap(part ->
                part.content()
                        .reduce(DataBuffer::write)
                        .publishOn(Schedulers.boundedElastic())
                        .flatMap(buffer -> {
                            try (InputStream inputStream = buffer.asInputStream()) {
                                Workbook workbook = WorkbookFactory.create(inputStream);

                                // Validar el archivo antes de procesar
                                validarArchivoExcel(workbook);

                                // Procesar cada hoja con métodos separados
                                procesarHojaPlanEstudios(workbook.getSheetAt(0));
                                cursoService.procesarHojaCursos(workbook.getSheetAt(1));
                                competenciaService.procesarHojaCompetencias(workbook.getSheetAt(2));
                                return Mono.empty();
                            } catch (Exception e) {
                                logger.error("Error al procesar el archivo Excel: {}", e.getMessage(), e);
                                return Mono.error(e); // Propagar error para manejarlo en el controlador
                            }
                        })
        ).then();
    }

    private void validarArchivoExcel(Workbook workbook) {
        if (workbook.getNumberOfSheets() < 3) {
            throw new IllegalArgumentException("El archivo debe contener al menos 3 hojas: Plan de Estudios, Cursos y Competencias.");
        }

        Sheet hojaPlanEstudios = workbook.getSheetAt(0);
        if (hojaPlanEstudios.getRow(0) == null || hojaPlanEstudios.getRow(0).getPhysicalNumberOfCells() < 7) {
            throw new IllegalArgumentException("La hoja Plan de Estudios no tiene el formato esperado.");
        }
    }


    public void procesarHojaPlanEstudios(Sheet hojaPlanEstudios) {
        for (Row row : hojaPlanEstudios) {
            if (row.getRowNum() == 0) continue; // Saltar encabezados

            try {
                // Extraer y mostrar los valores de cada celda
                String codigo = row.getCell(0).getStringCellValue();
                String descripcion = row.getCell(1).getStringCellValue();
                String vigencia = row.getCell(2).getStringCellValue();
                int institucionid = (int) row.getCell(3).getNumericCellValue();
                int departamentoid = (int) row.getCell(4).getNumericCellValue();
                int estado = (int) row.getCell(5).getNumericCellValue();
                int carreraid = (int) row.getCell(6).getNumericCellValue();

                // Convertir el estado a String
                String estadoString = String.valueOf(estado);

                // Log los valores extraídos
                logger.info("Procesando fila: {}", row.getRowNum());
                logger.info("Código: {}", codigo);
                logger.info("Descripción: {}", descripcion);
                logger.info("Vigencia: {}", vigencia);
                logger.info("Institución ID: {}", institucionid);
                logger.info("Departamento ID: {}", departamentoid);
                logger.info("Estado: {}", estadoString); // Mostrar el valor convertido
                logger.info("Carrera ID: {}", carreraid);
                // Llamar al método insertarPlanEstudios del repositorio
                planEstudiosRepository.insertarPlanEstudios(codigo, descripcion, vigencia, institucionid, departamentoid, estadoString, carreraid)
                        .doOnTerminate(() -> logger.info("Fila insertada"))
                        .subscribe();

                logger.info("Plan de estudios insertado: Código: {}, Descripción: {}", codigo, descripcion);
            } catch (Exception e) {
                logger.error("Error procesando fila de la Hoja Plan de Estudios {}: {}", row.getRowNum(), e.getMessage());
            }
        }
    }


}

