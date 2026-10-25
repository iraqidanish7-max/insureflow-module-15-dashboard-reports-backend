package com.insurance.reporting.serviceImplementation;

import com.insurance.reporting.service.DashboardService;
import com.insurance.reporting.client.ReportDataProvider;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service public class DashboardServiceImpl implements com.insurance.reporting.service.DashboardService {
    private final ReportDataProvider provider;
    public DashboardServiceImpl(ReportDataProvider provider){this.provider=provider;}
    public Map<String,Object> getSummary(){return provider.getSummary();}
    public Map<String,List<Map<String,Object>>> getCharts(){return provider.getCharts();}
}
