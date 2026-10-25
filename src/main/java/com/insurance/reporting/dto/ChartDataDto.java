package com.insurance.reporting.dto;

public class ChartDataDto {
    private String label;
    private Number value;

    public ChartDataDto() {
    }

    public ChartDataDto(String label, Number value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
