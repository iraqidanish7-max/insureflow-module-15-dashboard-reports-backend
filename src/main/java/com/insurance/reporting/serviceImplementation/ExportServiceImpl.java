package com.insurance.reporting.serviceImplementation;

import com.insurance.reporting.service.ExportService;
import com.insurance.reporting.client.ReportDataProvider;
import com.insurance.reporting.entity.ReportExecution;
import com.insurance.reporting.repository.ReportExecutionRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ExportServiceImpl implements com.insurance.reporting.service.ExportService {
    private final ReportDataProvider provider;
    private final ReportExecutionRepository executionRepository;

    public ExportServiceImpl(ReportDataProvider provider, ReportExecutionRepository executionRepository) {
        this.provider = provider;
        this.executionRepository = executionRepository;
    }

    public List<Map<String, Object>> rows(String category, LocalDate from, LocalDate to,
                                          String status, String branch, String product) {
        List<Map<String, Object>> rows = provider.getReport(category, from, to, status, branch, product);
        ReportExecution execution = new ReportExecution();
        execution.setReportId(reportId(category));
        execution.setExecutionStatus("Success");
        execution.setRequestedReport(category + " EXPORT");
        executionRepository.save(execution);
        return rows;
    }

    private Long reportId(String category) {
        return switch (category.toLowerCase()) {
            case "customer", "customers" -> 1L;
            case "policy", "policies" -> 2L;
            case "premium", "premiums" -> 3L;
            case "claim", "claims" -> 4L;
            case "underwriting" -> 5L;
            case "agent", "agents" -> 6L;
            default -> 7L;
        };
    }

    public byte[] csv(List<Map<String, Object>> rows) {
        StringBuilder output = new StringBuilder();
        if (rows.isEmpty()) {
            return "No data\n".getBytes(StandardCharsets.UTF_8);
        }
        List<String> keys = rows.get(0).keySet().stream().toList();
        output.append(keys.stream().map(this::escapeCsv).reduce((a, b) -> a + "," + b)
                .orElse(""));
        output.append("\r\n");
        for (Map<String, Object> row : rows) {
            output.append(keys.stream()
                    .map(key -> escapeCsv(String.valueOf(row.getOrDefault(key, ""))))
                    .reduce((a, b) -> a + "," + b).orElse(""));
            output.append("\r\n");
        }
        return output.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String escapeCsv(String value) {
        String safeValue = value == null ? "" : value.replace("\"", "\"\"");
        if (safeValue.contains(",") || safeValue.contains("\n") || safeValue.contains("\"")) {
            return "\"" + safeValue + "\"";
        }
        return safeValue;
    }

    public byte[] excel(List<Map<String, Object>> rows) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Report");
            if (!rows.isEmpty()) {
                List<String> keys = rows.get(0).keySet().stream().toList();
                Row header = sheet.createRow(0);
                for (int column = 0; column < keys.size(); column++) {
                    header.createCell(column).setCellValue(keys.get(column));
                }
                for (int index = 0; index < rows.size(); index++) {
                    Row excelRow = sheet.createRow(index + 1);
                    for (int column = 0; column < keys.size(); column++) {
                        excelRow.createCell(column).setCellValue(
                                String.valueOf(rows.get(index).getOrDefault(keys.get(column), "")));
                    }
                }
                for (int column = 0; column < keys.size(); column++) {
                    sheet.autoSizeColumn(column);
                }
            }
            workbook.write(output);
            return output.toByteArray();
        }
    }

    public byte[] pdf(List<Map<String, Object>> rows) throws IOException {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            document.addPage(new PDPage());
            try (PDPageContentStream content = new PDPageContentStream(document, document.getPage(0))) {
                content.beginText();
                content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
                content.newLineAtOffset(40, 750);
                content.showText("Insurance PAS Report - sample output");
                content.newLineAtOffset(0, -18);
                if (rows.isEmpty()) {
                    content.showText("No data");
                } else {
                    List<String> keys = rows.get(0).keySet().stream().toList();
                    content.showText(String.join(" | ", keys));
                    content.newLineAtOffset(0, -14);
                    int count = 0;
                    for (Map<String, Object> row : rows) {
                        String line = keys.stream()
                                .map(key -> String.valueOf(row.getOrDefault(key, "")))
                                .reduce((a, b) -> a + " | " + b).orElse("");
                        line = line.replaceAll("[^\\x20-\\x7E]", "?");
                        if (line.length() > 110) line = line.substring(0, 110);
                        content.showText(line);
                        content.newLineAtOffset(0, -13);
                        if (++count >= 45) break;
                    }
                }
                content.endText();
            }
            document.save(output);
            return output.toByteArray();
        }
    }
}
