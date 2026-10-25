package com.insurance.reporting.service;

import com.insurance.reporting.dto.ScheduleRequest;
import com.insurance.reporting.entity.ReportSchedule;
import java.util.List;

public interface ReportScheduleService {
    List<ReportSchedule> all();
    ReportSchedule create(ScheduleRequest request);
    ReportSchedule update(Long id, ScheduleRequest request);
    void delete(Long id);
}
