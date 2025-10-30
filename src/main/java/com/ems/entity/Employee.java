package com.ems.entity;

import com.ems.enums.Designation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name="employee_id")
    private String employeeId;

    @Column(name="name")
    private String employeeName;

    @Enumerated(EnumType.STRING)
    @Column(name="designation")
    private Designation employeeDesignation;

    @Column(name="email")
    private String employeeEmail;

    @Column(name="join_date")
    private LocalDate employeeJoiningDate;
}
