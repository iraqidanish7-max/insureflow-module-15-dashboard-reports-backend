package com.insurance.reporting.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report_schedule")
public class ReportSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;
    @Column(name = "report_id", nullable = false)
    private Long reportId;
    @Column(name = "schedule_type", nullable = false, length = 20)
    private String scheduleType;
    @Column(name = "next_run_date", nullable = false)
    private LocalDateTime nextRunDate;
    @Column(name = "email_to", nullable = false, length = 250)
    private String emailTo;
    @Column(nullable = false, length = 20)
    private String status = "Active";

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long v) {
        scheduleId = v;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long v) {
        reportId = v;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String v) {
        scheduleType = v;
    }

    public LocalDateTime getNextRunDate() {
        return nextRunDate;
    }

    public void setNextRunDate(LocalDateTime v) {
        nextRunDate = v;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String v) {
        emailTo = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String v) {
        status = v;
    }
}
