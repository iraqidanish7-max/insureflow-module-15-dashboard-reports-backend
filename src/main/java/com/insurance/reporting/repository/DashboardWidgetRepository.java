package com.insurance.reporting.repository;
import com.insurance.reporting.entity.DashboardWidget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DashboardWidgetRepository extends JpaRepository<DashboardWidget, Long> {
    List<DashboardWidget> findByStatusOrderBySequenceNoAsc(String status);
}
