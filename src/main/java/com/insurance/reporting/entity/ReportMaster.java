package com.insurance.reporting.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report_master")
public class ReportMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
    @Column(name = "report_name", nullable = false, length = 150)
    private String reportName;
    @Column(name = "report_type", nullable = false, length = 100)
    private String reportType;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    void onCreate() {
        if (createdDate == null) createdDate = LocalDateTime.now();
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long v) {
        reportId = v;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String v) {
        reportName = v;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String v) {
        reportType = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String v) {
        description = v;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime v) {
        createdDate = v;
    }
}
