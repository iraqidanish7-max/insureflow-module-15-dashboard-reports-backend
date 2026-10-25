package com.insurance.reporting.service;

import com.insurance.reporting.entity.ReportExecution;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Map<String,Object>> getReport(
            String category, LocalDate from, LocalDate to, String status, String branch, String product);
    List<ReportExecution> getExecutionHistory();
}
