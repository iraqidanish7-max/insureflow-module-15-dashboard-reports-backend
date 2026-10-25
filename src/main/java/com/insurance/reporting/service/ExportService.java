package com.insurance.reporting.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ExportService {
    List<Map<String, Object>> rows(
            String category, LocalDate from, LocalDate to, String status, String branch, String product);
    byte[] csv(List<Map<String, Object>> rows);
    byte[] excel(List<Map<String, Object>> rows) throws IOException;
    byte[] pdf(List<Map<String, Object>> rows) throws IOException;
}
