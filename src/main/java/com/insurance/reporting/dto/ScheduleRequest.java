package com.insurance.reporting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ScheduleRequest {
    @NotNull
    private Long reportId;
    @NotBlank
    private String scheduleType;
    @NotNull
    private LocalDateTime nextRunDate;
    @Email
    @NotBlank
    private String emailTo;

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
}
