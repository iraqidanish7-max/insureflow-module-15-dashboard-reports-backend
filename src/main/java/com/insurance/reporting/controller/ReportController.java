package com.insurance.reporting.controller;

import com.insurance.reporting.dto.ApiResponse;
import com.insurance.reporting.entity.ReportExecution;
import com.insurance.reporting.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public ApiResponse<List<Map<String, Object>>> customers(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return ApiResponse.success("Customer report (dummy data)",
                service.getReport("customers", from, to, status, branch, product));
    }

    @GetMapping("/policies")
    public ApiResponse<List<Map<String, Object>>> policies(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return ApiResponse.success("Policy report (dummy data)",
                service.getReport("policies", from, to, status, branch, product));
    }

    @GetMapping("/premiums")
    public ApiResponse<List<Map<String, Object>>> premiums(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return ApiResponse.success("Premium report (dummy data)",
                service.getReport("premiums", from, to, status, branch, product));
    }

    @GetMapping("/claims")
    public ApiResponse<List<Map<String, Object>>> claims(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return ApiResponse.success("Claims report (dummy data)",
                service.getReport("claims", from, to, status, branch, product));
    }

    @GetMapping("/underwriting")
    public ApiResponse<List<Map<String, Object>>> underwriting(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return ApiResponse.success("Underwriting report (dummy data)",
                service.getReport("underwriting", from, to, status, branch, product));
    }

    @GetMapping("/agents")
    public ApiResponse<List<Map<String, Object>>> agents(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return ApiResponse.success("Agent report (dummy data)",
                service.getReport("agents", from, to, status, branch, product));
    }

    @GetMapping("/financial")
    public ApiResponse<List<Map<String, Object>>> financial(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) String status, @RequestParam(required = false) String branch,
            @RequestParam(required = false) String product) {
        return ApiResponse.success("Financial report (dummy data)",
                service.getReport("financial", from, to, status, branch, product));
    }

    @GetMapping("/executions")
    public ApiResponse<List<ReportExecution>> executions() {
        return ApiResponse.success("Recent report execution history",
                service.getExecutionHistory());
    }
}
