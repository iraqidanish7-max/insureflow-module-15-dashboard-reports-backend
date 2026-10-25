package com.insurance.reporting.controller;

import com.insurance.reporting.dto.ApiResponse;
import com.insurance.reporting.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public ApiResponse<Map<String, Object>> summary() {
        return ApiResponse.success("Dashboard summary (dummy data)", service.getSummary());
    }

    @GetMapping("/kpi")
    public ApiResponse<Map<String, Object>> kpi() {
        return ApiResponse.success("Dashboard KPIs (dummy data)", service.getSummary());
    }

    @GetMapping("/charts")
    public ApiResponse<Map<String, List<Map<String, Object>>>> charts() {
        return ApiResponse.success("Chart data (dummy data)", service.getCharts());
    }
}
