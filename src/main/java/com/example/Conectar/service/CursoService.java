package com.example.Conectar.service;

import com.example.Conectar.DTO.CursoDTO;
import com.example.Conectar.controller.PlanEstudiosController;
import com.example.Conectar.repository.CursoRepository;
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
public class CursoService {
    private static final Logger logger = LoggerFactory.getLogger(PlanEstudiosController.class);
    private final CursoRepository cursoRepository;
    public void procesarHojaCursos(Sheet hojaCursos) {
        Flux.fromIterable(hojaCursos)
                .skip(1) // Omitir la primera fila si es encabezado
                .mapNotNull(row -> {
                    try {
                        logger.info("Insertando Curso, fila: {}", row.getRowNum());

                        Long numHorasCampo = null;
                        if (row.getCell(6) != null) {
                            if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                                numHorasCampo = (long) row.getCell(6).getNumericCellValue();
                            } else {
                                logger.warn("La celda de 'numHorasCampo' en la fila {} no es numérica. Valor ignorado.", row.getRowNum());
                            }
                        }
                        String sumilla = row.getCell(14) == null ? null : row.getCell(14).getStringCellValue();
                        String modalidad = row.getCell(15) == null ? null : row.getCell(15).getStringCellValue();
                        String etiquetas = row.getCell(16) == null ? null : row.getCell(16).getStringCellValue();

                        return CursoDTO.builder()
                                .codigo(row.getCell(0).getStringCellValue())
                                .nombre(row.getCell(1).getStringCellValue())
                                .tipo(row.getCell(2).getStringCellValue())
                                .numHorasTeoria((long) row.getCell(3).getNumericCellValue())
                                .numHorasPractica((long) row.getCell(4).getNumericCellValue())
                                .numHorasLaboratorio((long) row.getCell(5).getNumericCellValue())
                                .numHorasCampo(numHorasCampo) // Método para manejar celdas nulas o no numéricas
                                .numCreditos((long) row.getCell(7).getNumericCellValue())
                                .ciclo(row.getCell(8).getStringCellValue())
                                .estado((long) row.getCell(9).getNumericCellValue())
                                .periodoAcademicoId((long) row.getCell(10).getNumericCellValue())
                                .planEstudiosId((long) row.getCell(11).getNumericCellValue())
                                .institucionid((long) row.getCell(12).getNumericCellValue())
                                .departamentoid((long) row.getCell(13).getNumericCellValue())
                                .sumilla(sumilla)
                                .modalidad(modalidad)
                                .etiquetas(etiquetas)
                                .build();

                    } catch (Exception e) {
                        logger.error("Error al procesar fila {}: {}", row.getRowNum(), e.getMessage(), e);
                        return null; // Ignorar la fila errónea
                    }
                })
                .filter(Objects::nonNull) // Filtrar filas nulas o con errores
                .flatMap(curso -> cursoRepository.insertarCurso(
                        curso.getCodigo(),
                        curso.getNombre(),
                        curso.getTipo(),
                        curso.getNumHorasTeoria().intValue(),        // Conversión explícita
                        curso.getNumHorasPractica().intValue(),
                        curso.getNumHorasLaboratorio().intValue(),
                        curso.getNumHorasCampo() == null ? null : curso.getNumHorasCampo().intValue(),
                        curso.getNumCreditos(),
                        curso.getCiclo(),
                        curso.getEstado().intValue(),
                        curso.getPeriodoAcademicoId().intValue(),
                        curso.getPlanEstudiosId().intValue(),
                        curso.getInstitucionid().intValue(),
                        curso.getDepartamentoid().intValue(),
                        curso.getSumilla(),
                        curso.getModalidad(),
                        curso.getEtiquetas()
                ))
                .doOnNext(curso -> logger.info("Curso insertado: {}", curso))
                .doOnError(error -> logger.error("Error durante la inserción: {}", error.getMessage()))
                .then()
                .subscribe();
    }
}
