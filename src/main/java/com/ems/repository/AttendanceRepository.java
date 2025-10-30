package com.ems.repository;

import com.ems.entity.Attendance;
import com.ems.entity.Employee;
import com.ems.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployee_EmployeeId(String employeeId);
    Optional<Attendance> findByAttendanceStatus(AttendanceStatus attendanceStatus);
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);
}
