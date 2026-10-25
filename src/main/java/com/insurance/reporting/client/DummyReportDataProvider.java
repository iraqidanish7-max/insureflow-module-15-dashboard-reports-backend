package com.insurance.reporting.client;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Primary
public class DummyReportDataProvider implements ReportDataProvider {
    private final List<Map<String, Object>> customers = rows(row("customerId", 1001, "customerName", "Aarav Sharma", "city", "Mumbai", "state", "Maharashtra", "status", "Active", "product", "Health", "agent", "Riya Patil", "branch", "Mumbai Central", "date", "2026-10-02"), row("customerId", 1002, "customerName", "Meera Shah", "city", "Pune", "state", "Maharashtra", "status", "Active", "product", "Motor", "agent", "Kabir Khan", "branch", "Pune", "date", "2026-10-04"), row("customerId", 1003, "customerName", "Ishaan Verma", "city", "Nashik", "state", "Maharashtra", "status", "Inactive", "product", "Life", "agent", "Riya Patil", "branch", "Nashik", "date", "2026-10-06"), row("customerId", 1004, "customerName", "Sara Khan", "city", "Mumbai", "state", "Maharashtra", "status", "KYC_PENDING", "product", "Travel", "agent", "Aman Joshi", "branch", "Mumbai Central", "date", "2026-10-10"), row("customerId", 1005, "customerName", "Dev Patel", "city", "Thane", "state", "Maharashtra", "status", "Active", "product", "Property", "agent", "Kabir Khan", "branch", "Thane", "date", "2026-10-12"));
    private final List<Map<String, Object>> policies = rows(row("policyNumber", "HLT-2026-0001", "customerId", 1001, "product", "Health", "status", "Active", "branch", "Mumbai Central", "premium", 12000, "issueDate", "2026-10-02", "renewalDue", false), row("policyNumber", "MOT-2026-0002", "customerId", 1002, "product", "Motor", "status", "Active", "branch", "Pune", "premium", 8500, "issueDate", "2026-10-04", "renewalDue", true), row("policyNumber", "LIF-2026-0003", "customerId", 1003, "product", "Life", "status", "Expired", "branch", "Nashik", "premium", 22000, "issueDate", "2025-10-06", "renewalDue", true), row("policyNumber", "TRV-2026-0004", "customerId", 1004, "product", "Travel", "status", "Pending Payment", "branch", "Mumbai Central", "premium", 2500, "issueDate", "2026-10-10", "renewalDue", false), row("policyNumber", "PRO-2026-0005", "customerId", 1005, "product", "Property", "status", "Cancelled", "branch", "Thane", "premium", 18000, "issueDate", "2026-10-12", "renewalDue", false), row("policyNumber", "HLT-2026-0006", "customerId", 1001, "product", "Health", "status", "Active", "branch", "Mumbai Central", "premium", 15000, "issueDate", "2026-10-15", "renewalDue", false));
    private final List<Map<String, Object>> premiums = rows(row("paymentId", 5001, "policyNumber", "HLT-2026-0001", "amount", 12000, "status", "SUCCESS", "paymentDate", "2026-10-03", "agent", "Riya Patil", "branch", "Mumbai Central"), row("paymentId", 5002, "policyNumber", "MOT-2026-0002", "amount", 8500, "status", "SUCCESS", "paymentDate", "2026-10-05", "agent", "Kabir Khan", "branch", "Pune"), row("paymentId", 5003, "policyNumber", "TRV-2026-0004", "amount", 2500, "status", "PENDING", "paymentDate", "2026-10-10", "agent", "Aman Joshi", "branch", "Mumbai Central"), row("paymentId", 5004, "policyNumber", "HLT-2026-0006", "amount", 15000, "status", "SUCCESS", "paymentDate", "2026-10-16", "agent", "Riya Patil", "branch", "Mumbai Central"), row("paymentId", 5005, "policyNumber", "LIF-2026-0003", "amount", 22000, "status", "REFUND_PENDING", "paymentDate", "2026-10-18", "agent", "Riya Patil", "branch", "Nashik"));
    private final List<Map<String, Object>> claims = rows(row("claimId", 7001, "policyNumber", "HLT-2026-0001", "amount", 45000, "status", "SETTLED", "claimDate", "2026-10-03", "branch", "Mumbai Central", "investigation", "No"), row("claimId", 7002, "policyNumber", "MOT-2026-0002", "amount", 75000, "status", "PENDING", "claimDate", "2026-10-07", "branch", "Pune", "investigation", "Yes"), row("claimId", 7003, "policyNumber", "HLT-2026-0006", "amount", 18000, "status", "APPROVED", "claimDate", "2026-10-12", "branch", "Mumbai Central", "investigation", "No"), row("claimId", 7004, "policyNumber", "PRO-2026-0005", "amount", 95000, "status", "REJECTED", "claimDate", "2026-10-14", "branch", "Thane", "investigation", "Yes"));
    private final List<Map<String, Object>> underwriting = rows(row("caseId", 8001, "customerId", 1001, "status", "APPROVED", "risk", "LOW", "underwriter", "Nisha Rao", "caseDate", "2026-10-02"), row("caseId", 8002, "customerId", 1002, "status", "PENDING", "risk", "HIGH", "underwriter", "Amit Shah", "caseDate", "2026-10-04"), row("caseId", 8003, "customerId", 1003, "status", "REJECTED", "risk", "HIGH", "underwriter", "Amit Shah", "caseDate", "2026-10-06"), row("caseId", 8004, "customerId", 1004, "status", "MEDICAL_PENDING", "risk", "MEDIUM", "underwriter", "Nisha Rao", "caseDate", "2026-10-10"));
    private final List<Map<String, Object>> agents = rows(row("agent", "Riya Patil", "sales", 12, "premiumCollected", 49000, "activeCustomers", 18, "commission", 4900, "branch", "Mumbai Central"), row("agent", "Kabir Khan", "sales", 9, "premiumCollected", 32000, "activeCustomers", 15, "commission", 3200, "branch", "Pune"), row("agent", "Aman Joshi", "sales", 6, "premiumCollected", 18500, "activeCustomers", 10, "commission", 1850, "branch", "Mumbai Central"));

    private static Map<String, Object> row(Object... pairs) {
        Map<String, Object> m = new LinkedHashMap<>();
        for (int i = 0; i < pairs.length; i += 2) m.put(String.valueOf(pairs[i]), pairs[i + 1]);
        return m;
    }

    @SafeVarargs
    private static List<Map<String, Object>> rows(Map<String, Object>... r) {
        return new ArrayList<>(Arrays.asList(r));
    }

    @Override
    public List<Map<String, Object>> getReport(String category, LocalDate from, LocalDate to, String status, String branch, String product) {
        List<Map<String, Object>> source = switch (category.toLowerCase(Locale.ROOT)) {
            case "customer", "customers" -> customers;
            case "policy", "policies" -> policies;
            case "premium", "premiums" -> premiums;
            case "claim", "claims" -> claims;
            case "underwriting" -> underwriting;
            case "agent", "agents" -> agents;
            case "financial" -> new ArrayList<>() {{
                addAll(premiums);
                addAll(claims);
            }};
            default -> throw new IllegalArgumentException("Unsupported report category: " + category);
        };
        return source.stream().filter(r -> matches(r, "status", status)).filter(r -> matches(r, "branch", branch)).filter(r -> matches(r, "product", product)).filter(r -> inDateRange(r, from, to)).map(r -> (Map<String, Object>) new LinkedHashMap<>(r)).collect(Collectors.toList());
    }

    private boolean matches(Map<String, Object> row, String key, String filter) {
        return filter == null || filter.isBlank() || String.valueOf(row.getOrDefault(key, "")).equalsIgnoreCase(filter);
    }

    private boolean inDateRange(Map<String, Object> row, LocalDate from, LocalDate to) {
        Object raw = row.get("date");
        if (raw == null) raw = row.get("issueDate");
        if (raw == null) raw = row.get("paymentDate");
        if (raw == null) raw = row.get("claimDate");
        if (raw == null) raw = row.get("caseDate");
        if (raw == null) return true;
        LocalDate date = LocalDate.parse(String.valueOf(raw));
        return (from == null || !date.isBefore(from)) && (to == null || !date.isAfter(to));
    }

    @Override
    public Map<String, Object> getSummary() {
        long activePolicies = policies.stream().filter(p -> "Active".equals(p.get("status"))).count();
        BigDecimal collected = premiums.stream().filter(p -> "SUCCESS".equals(p.get("status"))).map(p -> new BigDecimal(String.valueOf(p.get("amount")))).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal pending = premiums.stream().filter(p -> "PENDING".equals(p.get("status"))).map(p -> new BigDecimal(String.valueOf(p.get("amount")))).reduce(BigDecimal.ZERO, BigDecimal::add);
        long settled = claims.stream().filter(c -> "SETTLED".equals(c.get("status"))).count();
        BigDecimal claimsPaid = claims.stream().filter(c -> "SETTLED".equals(c.get("status"))).map(c -> new BigDecimal(String.valueOf(c.get("amount")))).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalCustomers", customers.size());
        result.put("totalPolicies", policies.size());
        result.put("activePolicies", activePolicies);
        result.put("premiumCollected", collected);
        result.put("pendingPremium", pending);
        result.put("claimsRegistered", claims.size());
        result.put("claimsApproved", claims.stream().filter(c -> "APPROVED".equals(c.get("status"))).count());
        result.put("claimsRejected", claims.stream().filter(c -> "REJECTED".equals(c.get("status"))).count());
        result.put("claimsSettled", settled);
        result.put("outstandingClaims", claims.stream().filter(c -> !List.of("SETTLED", "REJECTED").contains(c.get("status"))).map(c -> new BigDecimal(String.valueOf(c.get("amount")))).reduce(BigDecimal.ZERO, BigDecimal::add));
        result.put("monthlyRevenue", collected);
        result.put("lossRatioPercent", collected.signum() == 0 ? BigDecimal.ZERO : claimsPaid.multiply(new BigDecimal("100")).divide(collected, 2, java.math.RoundingMode.HALF_UP));
        result.put("renewalDue", policies.stream().filter(p -> Boolean.TRUE.equals(p.get("renewalDue"))).count());
        result.put("topPerformingAgent", "Riya Patil");
        result.put("dataSource", "DUMMY_DATA");
        return result;
    }

    @Override
    public Map<String, List<Map<String, Object>>> getCharts() {
        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();
        result.put("premiumTrend", List.of(row("label", "Aug", "value", 24000), row("label", "Sep", "value", 31000), row("label", "Oct", "value", 39500)));
        result.put("policySales", List.of(row("label", "Aug", "value", 8), row("label", "Sep", "value", 11), row("label", "Oct", "value", 14)));
        result.put("policiesByProduct", List.of(row("label", "Health", "value", 2), row("label", "Motor", "value", 1), row("label", "Life", "value", 1), row("label", "Travel", "value", 1), row("label", "Property", "value", 1)));
        result.put("claimsByStatus", List.of(row("label", "SETTLED", "value", 1), row("label", "PENDING", "value", 1), row("label", "APPROVED", "value", 1), row("label", "REJECTED", "value", 1)));
        result.put("agentPerformance", List.of(row("label", "Riya Patil", "value", 12), row("label", "Kabir Khan", "value", 9), row("label", "Aman Joshi", "value", 6)));
        result.put("dataSource", List.of(row("label", "source", "value", "DUMMY_DATA")));
        return result;
    }
}
