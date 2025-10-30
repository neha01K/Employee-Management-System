package com.ems.repository;

import com.ems.entity.Employee;
import com.ems.enums.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeName(String employeeName);
    Optional<Employee> findByEmployeeDesignation(Designation designation);
    Optional<Employee> findByEmployeeId(String employeeId);
    Optional<Employee> findByEmployeeEmail(String employeeEmail);
    boolean existsByEmployeeEmail(String employeeEmail);
}
