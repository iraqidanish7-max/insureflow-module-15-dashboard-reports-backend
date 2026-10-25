package com.insurance.reporting.repository;
import com.insurance.reporting.entity.ReportSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReportScheduleRepository extends JpaRepository<ReportSchedule, Long> { }
