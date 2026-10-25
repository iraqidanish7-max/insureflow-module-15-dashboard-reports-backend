package com.insurance.reporting.repository;
import com.insurance.reporting.entity.AnalyticsSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AnalyticsSnapshotRepository extends JpaRepository<AnalyticsSnapshot, Long> {
    Optional<AnalyticsSnapshot> findTopByOrderBySnapshotDateDesc();
}
