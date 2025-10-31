package com.ems.repository;

import com.ems.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Long> {

    Optional<LoginDetails> findByEmployee_EmployeeId(String employeeId);
}
