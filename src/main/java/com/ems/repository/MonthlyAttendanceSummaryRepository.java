package com.ems.repository;

import com.ems.entity.MonthlyAttendanceSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthlyAttendanceSummaryRepository extends JpaRepository<MonthlyAttendanceSummary, Long> {

    Optional<MonthlyAttendanceSummary> findByEmployeeName(String employeeName);
    Optional<MonthlyAttendanceSummary> findByEmployeeNameAndEmployeeMonth(String employeeName, Integer month);

}
