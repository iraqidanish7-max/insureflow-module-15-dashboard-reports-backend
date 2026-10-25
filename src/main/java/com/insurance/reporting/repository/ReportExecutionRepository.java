package com.insurance.reporting.repository;
import com.insurance.reporting.entity.ReportExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ReportExecutionRepository extends JpaRepository<ReportExecution, Long> {
    List<ReportExecution> findTop100ByOrderByExecutionDateDesc();
}
