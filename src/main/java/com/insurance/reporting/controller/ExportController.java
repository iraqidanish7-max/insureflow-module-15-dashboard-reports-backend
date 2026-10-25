package com.insurance.reporting.controller;

import com.insurance.reporting.service.ExportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports/export")
public class ExportController {
    private final ExportService service;

    public ExportController(ExportService service) {
        this.service = service;
    }

    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<byte[]> csv(
            @RequestParam(defaultValue = "customers") String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return download(service.csv(service.rows(category, from, to, status, branch, product)),
                "text/csv", "report.csv");
    }

    @GetMapping(value = "/excel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> excel(
            @RequestParam(defaultValue = "customers") String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) throws IOException {
        return download(service.excel(service.rows(category, from, to, status, branch, product)),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "report.xlsx");
    }

    @GetMapping(value = "/pdf", produces = "application/pdf")
    public ResponseEntity<byte[]> pdf(
            @RequestParam(defaultValue = "customers") String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) throws IOException {
        return download(service.pdf(service.rows(category, from, to, status, branch, product)),
                "application/pdf", "report.pdf");
    }

    private ResponseEntity<byte[]> download(byte[] bytes, String contentType, String fileName) {
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(bytes);
    }
}
