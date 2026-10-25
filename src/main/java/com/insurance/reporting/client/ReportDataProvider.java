package com.insurance.reporting.client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/** Reporting boundary: dummy implementation is used until the owning services expose APIs. */
public interface ReportDataProvider {
    List<Map<String, Object>> getReport(
            String category, LocalDate from, LocalDate to, String status, String branch, String product);
    Map<String, Object> getSummary();
    Map<String, List<Map<String, Object>>> getCharts();
}
