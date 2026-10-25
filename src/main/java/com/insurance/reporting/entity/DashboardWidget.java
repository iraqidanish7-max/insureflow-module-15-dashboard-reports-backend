package com.insurance.reporting.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboard_widget")
public class DashboardWidget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "widget_id")
    private Long widgetId;
    @Column(name = "widget_name", nullable = false, length = 100)
    private String widgetName;
    @Column(name = "widget_type", nullable = false, length = 50)
    private String widgetType;
    @Column(name = "sequence_no", nullable = false)
    private Integer sequenceNo;
    @Column(nullable = false, length = 20)
    private String status = "Active";

    public Long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Long v) {
        widgetId = v;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String v) {
        widgetName = v;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String v) {
        widgetType = v;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer v) {
        sequenceNo = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String v) {
        status = v;
    }
}
