package com.fisiunmsm.grupo3.comp.application.service;

import com.fisiunmsm.grupo3.comp.domain.model.CompetenciaResponse;
import com.itextpdf.text.*;
// import com.itextpdf.text.Image;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    @Autowired
    private CompetenciaService competenciaService;

    public Mono<byte[]> generarReportePdf() {
        return Mono.zip(
                competenciaService.contarCompetenciasGenerales(),
                competenciaService.contarCompetenciasEspecificas(),
                competenciaService.obtenerCompetenciasGenerales().collectList(),
                competenciaService.obtenerCompetenciasEspecificas().collectList()
        ).flatMap(tuple -> {
            long totalGenerales = tuple.getT1();
            long totalEspecificas = tuple.getT2();
            long totalCompetencias = totalGenerales + totalEspecificas;
            List<CompetenciaResponse> competenciasGenerales = tuple.getT3();
            List<CompetenciaResponse> competenciasEspecificas = tuple.getT4();

            return Mono.fromCallable(() -> {
                try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                    Document document = new Document();
                    PdfWriter.getInstance(document, byteArrayOutputStream);
                    document.open();

                    // Encabezado del Reporte
                    document.add(new Paragraph("Reporte de Competencias"));
                    document.add(new Paragraph("Fecha de generación: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    document.add(new Paragraph("Usuario: User"));
                    document.add(new Paragraph(" ")); // Espacio en blanco

                    // Resumen General
                    document.add(new Paragraph("Resumen General"));
                    document.add(new Paragraph("Número total de competencias registradas: " + totalCompetencias));
                    document.add(new Paragraph(" ")); // Espacio en blanco

                    // Tabla de Competencias por Tipo
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    PdfPCell cell1 = new PdfPCell(new Phrase("Tipo"));
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell1);

                    PdfPCell cell2 = new PdfPCell(new Phrase("Total"));
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell2);

                    table.addCell("Generales");
                    table.addCell(String.valueOf(totalGenerales));

                    table.addCell("Específicas");
                    table.addCell(String.valueOf(totalEspecificas));

                    document.add(table);

                    // Competencias Detalladas
                    document.add(new Paragraph("Competencias"));
                    PdfPTable detailedTable = new PdfPTable(7);
                    detailedTable.setWidthPercentage(100);
                    detailedTable.setSpacingBefore(10f);
                    detailedTable.setSpacingAfter(10f);

                    String[] headers = {"Código", "Nombre", "Descripción", "Tipo", "Plan", "Institución", "Departamento"};
                    for (String header : headers) {
                        PdfPCell headerCell = new PdfPCell(new Phrase(header));
                        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        detailedTable.addCell(headerCell);
                    }

                    for (CompetenciaResponse competencia : competenciasGenerales) {
                        addCompetenciaToTable(detailedTable, competencia);
                    }
                    for (CompetenciaResponse competencia : competenciasEspecificas) {
                        addCompetenciaToTable(detailedTable, competencia);
                    }

                    document.add(detailedTable);

                    document.add(new Paragraph("Análisis Estadístico"));
                    
                    Image pieChart = Image.getInstance(createPieChart(totalGenerales, totalEspecificas));
                    document.add(pieChart);

                    
                    Image barChart = Image.getInstance(createBarChart(competenciasGenerales, competenciasEspecificas));
                    document.add(barChart);

                    // Competencias por Departamento
                    document.add(new Paragraph("Competencias por Departamento"));
                    PdfPTable deptTable = new PdfPTable(2);
                    deptTable.setWidthPercentage(100);
                    deptTable.setSpacingBefore(10f);
                    deptTable.setSpacingAfter(10f);

                    PdfPCell deptCell1 = new PdfPCell(new Phrase("Departamento"));
                    deptCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    deptTable.addCell(deptCell1);

                    PdfPCell deptCell2 = new PdfPCell(new Phrase("Competencias Totales"));
                    deptCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    deptTable.addCell(deptCell2);

                    Map<String, Long> competenciasPorDept = competenciasGenerales.stream()
                            .collect(Collectors.groupingBy(CompetenciaResponse::departamentoNombre, Collectors.counting()));
                    competenciasPorDept.putAll(competenciasEspecificas.stream()
                            .collect(Collectors.groupingBy(CompetenciaResponse::departamentoNombre, Collectors.counting())));

                    for (Map.Entry<String, Long> entry : competenciasPorDept.entrySet()) {
                        deptTable.addCell(entry.getKey());
                        deptTable.addCell(String.valueOf(entry.getValue()));
                    }

                    document.add(deptTable);

                    document.add(new Paragraph("Competencias Faltantes o Desbalanceadas"));
                    for (Map.Entry<String, Long> entry : competenciasPorDept.entrySet()) {
                        if (entry.getValue() == 0) {
                            document.add(new Paragraph("Departamento sin competencias registradas: " + entry.getKey()));
                        } else if (entry.getValue() < 5) {
                            document.add(new Paragraph("Departamento con pocas competencias: " + entry.getKey() + " (" + entry.getValue() + " competencias)"));
                        }
                    }

                    document.close();

                    return byteArrayOutputStream.toByteArray();
                } catch (DocumentException | IOException e) {
                    throw new RuntimeException("Error al generar el PDF", e);
                }
            });
        });
    }

    private void addCompetenciaToTable(PdfPTable table, CompetenciaResponse competencia) {
        table.addCell(competencia.codigo());
        table.addCell(competencia.nombre());
        table.addCell(competencia.descripcion());
        table.addCell(competencia.tipo());
        table.addCell(competencia.planNombre());
        table.addCell(competencia.institucionNombre());
        table.addCell(competencia.departamentoNombre());
    }

    private byte[] createPieChart(long totalGenerales, long totalEspecificas) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Generales", totalGenerales);
        dataset.setValue("Específicas", totalEspecificas);

        JFreeChart chart = ChartFactory.createPieChart(
                "Distribución de Competencias por Tipo",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Generales", new Color(0, 128, 0));
        plot.setSectionPaint("Específicas", new Color(0, 0, 255));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(byteArrayOutputStream, chart, 500, 300);
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] createBarChart(List<CompetenciaResponse> competenciasGenerales, List<CompetenciaResponse> competenciasEspecificas) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (CompetenciaResponse competencia : competenciasGenerales) {
            dataset.addValue(1, "Generales", competencia.institucionNombre());
        }

        for (CompetenciaResponse competencia : competenciasEspecificas) {
            dataset.addValue(1, "Específicas", competencia.institucionNombre());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Competencias por Institución",
                "Institución",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 128, 0));
        renderer.setSeriesPaint(1, new Color(0, 0, 255));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(byteArrayOutputStream, chart, 500, 300);
        return byteArrayOutputStream.toByteArray();
    }
}
