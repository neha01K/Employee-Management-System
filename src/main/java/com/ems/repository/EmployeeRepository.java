package com.ems.repository;

import com.ems.entity.Employee;
import com.ems.enums.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    Optional<Employee> findById(Long employeeId);

    Optional<Employee> findByName(String name);
    Optional<Employee> findByDesignation(Designation designation);
    Optional<Employee> findByEmployeeId(String employeeId);
}
