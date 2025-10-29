package com.ems.repository;

import com.ems.entity.Employee;
import com.ems.entity.LeaveHistory;
import com.ems.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Long> {

    Optional<LeaveHistory> findByEmployee(Employee employee);
    Optional<LeaveHistory> findByStatus(LeaveStatus leaveStatus);
    Optional<LeaveHistory> findByAppliedDate(LocalDate appliedDate);
}
