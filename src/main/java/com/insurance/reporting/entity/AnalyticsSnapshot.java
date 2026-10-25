package com.insurance.reporting.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "analytics_snapshot")
public class AnalyticsSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snapshot_id")
    private Long snapshotId;
    @Column(name = "snapshot_date", nullable = false)
    private LocalDate snapshotDate;
    @Column(name = "total_customers")
    private Integer totalCustomers;
    @Column(name = "total_policies")
    private Integer totalPolicies;
    @Column(name = "premium_collected", precision = 15, scale = 2)
    private BigDecimal premiumCollected;
    @Column(name = "claims_paid", precision = 15, scale = 2)
    private BigDecimal claimsPaid;
    @Column(precision = 15, scale = 2)
    private BigDecimal revenue;

    public Long getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Long v) {
        snapshotId = v;
    }

    public LocalDate getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(LocalDate v) {
        snapshotDate = v;
    }

    public Integer getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(Integer v) {
        totalCustomers = v;
    }

    public Integer getTotalPolicies() {
        return totalPolicies;
    }

    public void setTotalPolicies(Integer v) {
        totalPolicies = v;
    }

    public BigDecimal getPremiumCollected() {
        return premiumCollected;
    }

    public void setPremiumCollected(BigDecimal v) {
        premiumCollected = v;
    }

    public BigDecimal getClaimsPaid() {
        return claimsPaid;
    }

    public void setClaimsPaid(BigDecimal v) {
        claimsPaid = v;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal v) {
        revenue = v;
    }
}
