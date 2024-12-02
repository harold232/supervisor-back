package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.*;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.CompetenciaTable;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.DepartamentoTable;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.InstitucionTable;
import com.fisiunmsm.grupo3.comp.infraestructure.mapper.PlanEstudiosTable;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.CompetenciaRepository;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.DepartamentoRepository;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.InstitucionRepository;
import com.fisiunmsm.grupo3.comp.infraestructure.repository.PlanEstudiosRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.csv.CSVParser;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private PlanEstudiosRepository planEstudiosRepository;
    @Autowired
    private InstitucionRepository institucionRepository;

    public Mono<Competencia> crearCompetenciaGeneral(CompetenciaRegister competenciaRegister) {
        Competencia competencia = new Competencia(
                null,
                competenciaRegister.codigo(),
                competenciaRegister.nombre(),
                competenciaRegister.descripcion(),
                competenciaRegister.planid(),
                competenciaRegister.institucionid(),
                competenciaRegister.departamentoid(),
                "G"
        );
        CompetenciaTable competenciaEntity = CompetenciaTable.fromDomainModel(competencia);
        return competenciaRepository.save(competenciaEntity)
                .map(CompetenciaTable::toDomainModel);
    }

    public Mono<Competencia> crearCompetenciaEspecifica(CompetenciaRegister competenciaRegister) {
        Competencia competencia = new Competencia(
                null,
                competenciaRegister.codigo(),
                competenciaRegister.nombre(),
                competenciaRegister.descripcion(),
                competenciaRegister.planid(),
                competenciaRegister.institucionid(),
                competenciaRegister.departamentoid(),
                "E"
        );
        CompetenciaTable competenciaEntity = CompetenciaTable.fromDomainModel(competencia);
        return competenciaRepository.save(competenciaEntity).map(CompetenciaTable::toDomainModel);
    }

    public Flux<CompetenciaResponse> obtenerCompetenciasGenerales() {
        return competenciaRepository.findAll()
                .filter(competencia -> "G".equals(competencia.getTipo()))
                .flatMap(this::mapToCompetenciaResponse);
    }

    public Flux<CompetenciaResponse> obtenerCompetenciasEspecificas() {
        return competenciaRepository.findAll()
                .filter(competencia -> "E".equals(competencia.getTipo()))
                .flatMap(this::mapToCompetenciaResponse);
    }

    private Mono<CompetenciaResponse> mapToCompetenciaResponse(CompetenciaTable competencia) {
        Mono<String> departamentoNombre = departamentoRepository.findById(competencia.getDepartamentoid())
                .map(DepartamentoTable::getNombre)
                .defaultIfEmpty("Dept. sin asignar");

        Mono<String> planNombre = planEstudiosRepository.findById(competencia.getPlanid())
                .map(PlanEstudiosTable::getDescripcion)
                .defaultIfEmpty("Plan sin aisgnar");

        Mono<String> institucionNombre = institucionRepository.findById(competencia.getInstitucionid())
                .map(InstitucionTable::getNombreCorto)
                .defaultIfEmpty("Institución sin asignar");


        return Mono.zip(departamentoNombre, planNombre, institucionNombre)
                .map(tuple -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        tuple.getT2(),
                        competencia.getInstitucionid(),
                        tuple.getT3(),
                        competencia.getDepartamentoid(),
                        tuple.getT1(),
                        competencia.getTipo()
                ));
    }

    public Mono<Competencia> actualizarCompetencia(Integer id, CompetenciaRegister competenciaRegister) {
        return competenciaRepository.findById(id)
                .flatMap(competencia -> {
                    competencia.setCodigo(competenciaRegister.codigo());
                    competencia.setNombre(competenciaRegister.nombre());
                    competencia.setDescripcion(competenciaRegister.descripcion());
                    competencia.setPlanid(competenciaRegister.planid());
                    competencia.setInstitucionid(competenciaRegister.institucionid());
                    competencia.setDepartamentoid(competenciaRegister.departamentoid());
                    return competenciaRepository.save(competencia);
                })
                .map(CompetenciaTable::toDomainModel)
                .switchIfEmpty(Mono.error(new RuntimeException("Competencia no encontrada")));
    }

    public Mono<Void> eliminarCompetencia(Integer id) {
        return competenciaRepository.findById(id)
                .flatMap(competencia -> competenciaRepository.delete(competencia));
                //.then()
                //.onErrorResume(e -> Mono.empty());
    }

    public Mono<CompetenciaResponse> obtenerCompetenciaPorId(Integer id) {
        return competenciaRepository.findById(id)
                .flatMap(this::mapToCompetenciaResponse)
                .switchIfEmpty(Mono.error(new RuntimeException("Competencia no encontrada")));
    }

    public Flux<CompetenciaResumen> obtenerCompetencias() {
        return competenciaRepository.findAll()
                .map(competencia -> new CompetenciaResumen(
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        "G".equals(competencia.getTipo()) ? "General" : "Específica"
                ));
    }

    public Mono<Long> contarCompetenciasGenerales() {
        return competenciaRepository.findAll()
                .filter(competencia -> "G".equals(competencia.getTipo()))
                .count();
    }

    public Mono<Long> contarCompetenciasEspecificas() {
        return competenciaRepository.findAll()
                .filter(competencia -> "E".equals(competencia.getTipo()))
                .count();
    }

    public Flux<Competencia> buscarCompetencias(String tipo, Integer departamento, Integer institucion) {
        return competenciaRepository.buscarCompetencias(tipo, departamento, institucion)
                .map(CompetenciaTable::toDomainModel);
    }

    public Mono<Void> importarCompetenciasCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            return Flux.fromIterable(csvParser.getRecords())
                    .map(this::csvRecordToCompetencia)
                    .flatMap(competenciaRepository::save)
                    .then();
        } catch (IOException e) {
            return Mono.error(new RuntimeException("Error processing CSV file", e));
        }
    }

    private CompetenciaTable csvRecordToCompetencia(CSVRecord record) {
        CompetenciaTable competencia = new CompetenciaTable(
                null,
                record.get("codigo"),
                record.get("nombre"),
                record.get("descripcion"),
                Integer.parseInt(record.get("planid")),
                Integer.parseInt(record.get("institucionid")),
                Integer.parseInt(record.get("departamentoid")),
                record.get("tipo")
        );
        System.out.println("Procesando competencia: " + competencia);
        return competencia;
    }

    public Mono<Void> importarCompetenciasExcel(MultipartFile file) {
        return Mono.fromCallable(() -> {
            try (InputStream inputStream = file.getInputStream();
                 Workbook workbook = new XSSFWorkbook(inputStream)) {

                Sheet sheet = workbook.getSheetAt(0);
                Flux<CompetenciaTable> competenciaFlux = Flux.fromIterable(sheet)
                        .skip(1) // Skip header row
                        .map(this::excelRowToCompetencia)
                        .flatMap(competenciaRepository::save);

                return competenciaFlux.then();
            } catch (IOException e) {
                return Mono.error(new RuntimeException("Error processing Excel file", e));
            }
        }).then();
    }

    private CompetenciaTable excelRowToCompetencia(Row row) {
        return new CompetenciaTable(
                null,
                row.getCell(0).getStringCellValue(),
                row.getCell(1).getStringCellValue(),
                row.getCell(2).getStringCellValue(),
                (int) row.getCell(3).getNumericCellValue(),
                (int) row.getCell(4).getNumericCellValue(),
                (int) row.getCell(5).getNumericCellValue(),
                row.getCell(6).getStringCellValue()
        );
    }

    public Flux<EstadisticasDTO> obtenerEstadisticas() {
        return competenciaRepository.obtenerEstadisticas();
    }

    public Flux<CompetenciasPorCursoDTO> obtenerCompetenciasPorCursoYTipo() {
        return competenciaRepository.obtenerCompetenciasPorCursoYTipo();
    }

    public Flux<PromedioCreditosHorasDTO> obtenerPromedioCreditosYHoras() {
        return competenciaRepository.obtenerPromedioCreditosYHoras();
    }
/*
    private Mono<CompetenciaResponse> mapToCompetenciaResponse(CompetenciaTable competencia) {
        return departamentoRepository.findById(competencia.getDepartamentoid())
                .map(departamento -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        competencia.getInstitucionid(),
                        competencia.getDepartamentoid(),
                        departamento.getNombre(),
                        competencia.getTipo()
                ));
    }*/
/*
    public Flux<CompetenciaResponse> obtenerCompetenciasGenerales() {
        return competenciaRepository.findAll()
                .filter(competencia -> "G".equals(competencia.getTipo())) // Filter for general competencies
                .map(CompetenciaTable::toDomainModel)
                .map(competencia -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        competencia.getInstitucionid(),
                        competencia.getDepartamentoid(),
                        competencia.getTipo()
                ));
    }

    public Flux<CompetenciaResponse> obtenerCompetenciasEspecificas() {
        return competenciaRepository.findAll()
                .filter(competencia -> "E".equals(competencia.getTipo()))
                .map(CompetenciaTable::toDomainModel)
                .map(competencia -> new CompetenciaResponse(
                        competencia.getId(),
                        competencia.getCodigo(),
                        competencia.getNombre(),
                        competencia.getDescripcion(),
                        competencia.getPlanid(),
                        competencia.getInstitucionid(),
                        competencia.getDepartamentoid(),
                        competencia.getTipo()
                ));
    }*/
}
