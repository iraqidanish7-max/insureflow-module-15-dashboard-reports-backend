package com.insurance.reporting.serviceImplementation;

import com.insurance.reporting.client.ReportDataProvider;
import com.insurance.reporting.entity.ReportExecution;
import com.insurance.reporting.repository.ReportExecutionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements com.insurance.reporting.service.ReportService {
    private final ReportDataProvider provider;
    private final ReportExecutionRepository executionRepository;

    public ReportServiceImpl(ReportDataProvider provider, ReportExecutionRepository executionRepository) {
        this.provider = provider;
        this.executionRepository = executionRepository;
    }

    public List<Map<String, Object>> getReport(
            String category, LocalDate from, LocalDate to, String status, String branch, String product) {
        List<Map<String, Object>> result = provider.getReport(category, from, to, status, branch, product);
        ReportExecution execution = new ReportExecution();
        execution.setReportId(reportId(category));
        execution.setExecutionStatus("Success");
        execution.setRequestedReport(category);
        executionRepository.save(execution);
        return result;
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

    public List<ReportExecution> getExecutionHistory() {
        return executionRepository.findTop100ByOrderByExecutionDateDesc();
    }
}
