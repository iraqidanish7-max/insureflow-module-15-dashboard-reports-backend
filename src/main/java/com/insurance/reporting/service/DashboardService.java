package com.insurance.reporting.service;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String,Object> getSummary();
    Map<String,List<Map<String,Object>>> getCharts();
}
