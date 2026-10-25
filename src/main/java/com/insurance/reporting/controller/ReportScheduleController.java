package com.insurance.reporting.controller;

import com.insurance.reporting.dto.ApiResponse;
import com.insurance.reporting.dto.ScheduleRequest;
import com.insurance.reporting.entity.ReportSchedule;
import com.insurance.reporting.service.ReportScheduleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports/schedule")
public class ReportScheduleController {
    private final ReportScheduleService service;

    public ReportScheduleController(ReportScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<ReportSchedule>> all() {
        return ApiResponse.success("Report schedules", service.all());
    }

    @PostMapping
    public ApiResponse<ReportSchedule> create(@Valid @RequestBody ScheduleRequest request) {
        return ApiResponse.success("Schedule saved. This does not yet trigger automatic delivery.",
                service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ReportSchedule> update(@PathVariable Long id, @Valid @RequestBody ScheduleRequest request) {
        return ApiResponse.success("Schedule updated",
                service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success("Schedule marked Inactive", null);
    }
}
