package com.insurance.reporting.serviceImplementation;

import com.insurance.reporting.dto.ScheduleRequest;
import com.insurance.reporting.entity.ReportSchedule;
import com.insurance.reporting.repository.ReportMasterRepository;
import com.insurance.reporting.repository.ReportScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReportScheduleServiceImpl implements com.insurance.reporting.service.ReportScheduleService {
    private static final List<String> TYPES = List.of("Daily", "Weekly", "Monthly", "Quarterly", "Yearly");
    private final ReportScheduleRepository schedules;
    private final ReportMasterRepository reports;

    public ReportScheduleServiceImpl(ReportScheduleRepository schedules, ReportMasterRepository reports) {
        this.schedules = schedules;
        this.reports = reports;
    }

    public List<ReportSchedule> all() {
        return schedules.findAll();
    }

    public ReportSchedule create(ScheduleRequest request) {
        validate(request);
        ReportSchedule s = new ReportSchedule();
        copy(request, s);
        return schedules.save(s);
    }

    public ReportSchedule update(Long id, ScheduleRequest request) {
        validate(request);
        ReportSchedule s = schedules.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));
        copy(request, s);
        return schedules.save(s);
    }

    public void delete(Long id) {
        ReportSchedule s = schedules.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));
        s.setStatus("Inactive");
        schedules.save(s);
    }

    private void validate(ScheduleRequest r) {
        if (!TYPES.contains(r.getScheduleType()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "scheduleType must be Daily, Weekly, Monthly, Quarterly or Yearly");
        if (!reports.existsById(r.getReportId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "reportId does not exist in report_master");
    }

    private void copy(ScheduleRequest r, ReportSchedule s) {
        s.setReportId(r.getReportId());
        s.setScheduleType(r.getScheduleType());
        s.setNextRunDate(r.getNextRunDate());
        s.setEmailTo(r.getEmailTo());
        s.setStatus("Active");
    }
}
