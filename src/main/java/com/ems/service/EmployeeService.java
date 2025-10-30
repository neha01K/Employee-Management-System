package com.ems.service;


import com.ems.entity.Employee;
import com.ems.entity.LeaveBalance;
import com.ems.entity.LoginDetails;
import com.ems.enums.Designation;
import com.ems.enums.LeaveType;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.LeaveBalanceRepository;
import com.ems.repository.LeaveHistoryRepository;
import com.ems.repository.LoginDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LoginDetailsRepository loginDetailsRepository;
    private final LeaveHistoryRepository leaveHistoryRepository;

    @Transactional
    public Employee registerEmployee(Employee employee, String password) {

        if (employeeRepository.existsByEmployeeEmail(employee.getEmployeeEmail())) {
            throw new RuntimeException("Employee already exists");
        }

        Employee savedEmployee = employeeRepository.save(employee);

        LoginDetails loginDetails = LoginDetails.builder()
                .employee(savedEmployee)
                .password(password)
                .build();
        loginDetailsRepository.save(loginDetails);

        LeaveBalance leaveBalance = calculateLeaveBalance(savedEmployee);
        leaveBalanceRepository.save(leaveBalance);

        return savedEmployee;
    }

    @Transactional
    private LeaveBalance calculateLeaveBalance(Employee employee) {

        LocalDate joiningDate = employee.getEmployeeJoiningDate();

        int monthsRemaining = 12 - joiningDate.getMonthValue() +1;
        float casualLeaveBalance = Math.min(monthsRemaining*1.25f, 15);
        int sickLeaveBalance = Math.min(monthsRemaining, 12);

        return LeaveBalance.builder()
                .employee(employee)
                .casualLeave(casualLeaveBalance)
                .sickLeave(sickLeaveBalance)
                .maternityLeave(2)
                .parentalLeave(2)
                .build();
    }

    public Employee getEmployeeById(String employeeId){
        return  employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(()->new RuntimeException("Employee not found!"));
    }

    public Employee getEmployeeByEmail(String employeeEmail){
        return employeeRepository.findByEmployeeEmail(employeeEmail)
                .orElseThrow(()->new RuntimeException("Employee not found!"));
    }

    public Employee getEmployeeByName(String employeeName) {
        return employeeRepository.findByEmployeeName(employeeName)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
    }


    @Transactional
    public Employee updateEmployeeProfile(String employeeId, Employee updatedEmployee) {
        Employee employee = getEmployeeById(employeeId);

        employee.setEmployeeName(updatedEmployee.getEmployeeName());
        employee.setEmployeeDesignation(updatedEmployee.getEmployeeDesignation());
        employee.setEmployeeEmail(updatedEmployee.getEmployeeEmail());
        employee.setEmployeeJoiningDate(updatedEmployee.getEmployeeJoiningDate());

        return employeeRepository.save(employee);
    }

    public LeaveBalance getLeaveBalance(String employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return leaveBalanceRepository.findByEmployee(employee)
                .orElseThrow(() -> new RuntimeException("This employee's leave balance not found!"));
    }

    @Transactional
    public void deductLeaveFromLeaveBalance(Employee employee, int numberOfLeaveDays, LeaveType leaveType){

        LeaveBalance leaveBalance  = leaveBalanceRepository.findByEmployee(employee)
                                     .orElseThrow(()-> new RuntimeException("This employee's leave balance not found!"));


        switch(leaveType){
            case CASUAL_LEAVE -> leaveBalance.setCasualLeave(
                    leaveBalance.getCasualLeave()-numberOfLeaveDays);

            case SICK_LEAVE -> leaveBalance.setSickLeave(
              leaveBalance.getSickLeave() - numberOfLeaveDays);

            case MATERNITY_LEAVE -> leaveBalance.setMaternityLeave(
                    leaveBalance.getMaternityLeave() - numberOfLeaveDays);

            case PARENTAL_LEAVE -> leaveBalance.setParentalLeave(
                    leaveBalance.getParentalLeave() - numberOfLeaveDays);

            case ANNUAL_LEAVE -> leaveBalance.setAnnualLeave(
                    leaveBalance.getAnnualLeave() - numberOfLeaveDays);
        }
    }


    public boolean canThisEmployeeApproveLeave(Employee leaveApprover, Employee leaveApplicant) {
        return switch (leaveApprover.getEmployeeDesignation()) {
            case MANAGER -> true;
            case LEAD -> leaveApplicant.getEmployeeDesignation() == Designation.EXECUTIVE;
            case EXECUTIVE -> false;
        };
    }

    public boolean validateLogin(String employeeId, String password) {
        LoginDetails loginDetails = loginDetailsRepository.findByEmployee_EmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        return Objects.equals(password, loginDetails.getPassword());
    }

}