package com.insurance.reporting.dto;

import java.math.BigDecimal;

public class DashboardSummaryDto {
    private long totalCustomers, totalPolicies, activePolicies, claimsRegistered, claimsApproved,
            claimsRejected, claimsSettled, renewalDue;
    private BigDecimal premiumCollected, pendingPremium, outstandingClaims, monthlyRevenue, lossRatio;
    private String topPerformingAgent;

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long v) {
        totalCustomers = v;
    }

    public long getTotalPolicies() {
        return totalPolicies;
    }

    public void setTotalPolicies(long v) {
        totalPolicies = v;
    }

    public long getActivePolicies() {
        return activePolicies;
    }

    public void setActivePolicies(long v) {
        activePolicies = v;
    }

    public long getClaimsRegistered() {
        return claimsRegistered;
    }

    public void setClaimsRegistered(long v) {
        claimsRegistered = v;
    }

    public long getClaimsApproved() {
        return claimsApproved;
    }

    public void setClaimsApproved(long v) {
        claimsApproved = v;
    }

    public long getClaimsRejected() {
        return claimsRejected;
    }

    public void setClaimsRejected(long v) {
        claimsRejected = v;
    }

    public long getClaimsSettled() {
        return claimsSettled;
    }

    public void setClaimsSettled(long v) {
        claimsSettled = v;
    }

    public long getRenewalDue() {
        return renewalDue;
    }

    public void setRenewalDue(long v) {
        renewalDue = v;
    }

    public BigDecimal getPremiumCollected() {
        return premiumCollected;
    }

    public void setPremiumCollected(BigDecimal v) {
        premiumCollected = v;
    }

    public BigDecimal getPendingPremium() {
        return pendingPremium;
    }

    public void setPendingPremium(BigDecimal v) {
        pendingPremium = v;
    }

    public BigDecimal getOutstandingClaims() {
        return outstandingClaims;
    }

    public void setOutstandingClaims(BigDecimal v) {
        outstandingClaims = v;
    }

    public BigDecimal getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(BigDecimal v) {
        monthlyRevenue = v;
    }

    public BigDecimal getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(BigDecimal v) {
        lossRatio = v;
    }

    public String getTopPerformingAgent() {
        return topPerformingAgent;
    }

    public void setTopPerformingAgent(String v) {
        topPerformingAgent = v;
    }
}
