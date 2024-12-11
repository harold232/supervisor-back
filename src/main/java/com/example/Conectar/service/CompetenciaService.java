package com.example.Conectar.service;

import com.example.Conectar.DTO.CompetenciaDTO;
import com.example.Conectar.repository.CompetenciaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompetenciaService {
    private static final Logger logger = LoggerFactory.getLogger(CompetenciaService.class);
    private final CompetenciaRepository competenciaRepository;

    public void procesarHojaCompetencias(Sheet hojaCompetencias) {
        Flux.fromIterable(hojaCompetencias)
                .skip(1) // Omitir la primera fila si es encabezado
                .mapNotNull(row -> {
                    try {
                        logger.info("Insertando Competencia, fila: {}", row.getRowNum());

                        // Procesar las celdas de la fila y mapearlas a CompetenciaDTO
                        return CompetenciaDTO.builder()
                                .codigo(row.getCell(0).getStringCellValue())
                                .nombre(row.getCell(1).getStringCellValue())
                                .descripcion(row.getCell(2).getStringCellValue())
                                .planid((long) row.getCell(3).getNumericCellValue())
                                .institucionid((long) row.getCell(4).getNumericCellValue())
                                .departamentoid((long) row.getCell(5).getNumericCellValue())
                                .tipo(row.getCell(6) == null ? null : row.getCell(6).getStringCellValue())
                                .build();

                    } catch (Exception e) {
                        logger.error("Error al procesar fila {}: {}", row.getRowNum(), e.getMessage(), e);
                        return null; // Ignorar la fila errónea
                    }
                })
                .filter(Objects::nonNull) // Filtrar filas nulas o con errores
                .flatMap(competencia -> competenciaRepository.insertarCompetencia(
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        competencia.getInstitucionid(),
                        competencia.getDepartamentoid(),
                        competencia.getTipo()
                ))
                .doOnNext(competencia -> logger.info("Competencia insertada: {}", competencia))
                .doOnError(error -> logger.error("Error durante la inserción: {}", error.getMessage()))
                .then()
                .subscribe();
    }

}
