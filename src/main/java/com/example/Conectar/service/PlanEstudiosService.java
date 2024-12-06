package com.example.Conectar.service;

import com.example.Conectar.controller.PlanEstudiosController;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.example.Conectar.repository.PlanEstudiosRepository;
import reactor.core.scheduler.Schedulers;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class PlanEstudiosService {

    private static final Logger logger = LoggerFactory.getLogger(PlanEstudiosController.class);

    private final PlanEstudiosRepository planEstudiosRepository;

    public Mono<Void> cargarPlanEstudiosDesdeExcel(Mono<FilePart> filePart) {
        return filePart.flatMap(part ->
                part.content()
                        .reduce(DataBuffer::write)
                        .publishOn(Schedulers.boundedElastic())
                        .publishOn(Schedulers.boundedElastic())
                        .map(buffer -> {
                            try (InputStream inputStream = buffer.asInputStream()) {
                                Workbook workbook = WorkbookFactory.create(inputStream);
                                Sheet sheet = workbook.getSheetAt(0);

                                // Iterar sobre cada fila del archivo Excel
                                for (Row row : sheet) {
                                    if (row.getRowNum() == 0) continue; // Saltar la primera fila de encabezados

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
                                }

                                return Mono.empty(); // O puedes retornar un Mono<Void> si lo prefieres
                            } catch (Exception e) {
                                logger.error("Error al procesar el archivo Excel: {}", e.getMessage(), e);
                                return Mono.error(e);
                            }
                        })
        ).then();
    }
}
