package com.insurance.reporting.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report_execution")
public class ReportExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execution_id")
    private Long executionId;
    @Column(name = "report_id", nullable = false)
    private Long reportId;
    @Column(name = "executed_by")
    private Long executedBy;
    @Column(name = "execution_date", nullable = false)
    private LocalDateTime executionDate;
    @Column(name = "execution_status", nullable = false, length = 20)
    private String executionStatus;
    @Column(name = "file_path", length = 500)
    private String filePath;
    @Column(name = "requested_report", length = 100)
    private String requestedReport;

    @PrePersist
    void onCreate() {
        if (executionDate == null) executionDate = LocalDateTime.now();
    }

    public Long getExecutionId() {
        return executionId;
    }

    public void setExecutionId(Long v) {
        executionId = v;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long v) {
        reportId = v;
    }

    public Long getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(Long v) {
        executedBy = v;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime v) {
        executionDate = v;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String v) {
        executionStatus = v;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String v) {
        filePath = v;
    }

    public String getRequestedReport() {
        return requestedReport;
    }

    public void setRequestedReport(String v) {
        requestedReport = v;
    }
}
