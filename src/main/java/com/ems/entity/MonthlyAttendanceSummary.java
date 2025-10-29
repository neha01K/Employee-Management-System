package com.ems.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyAttendanceSummary {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName="employee_id", nullable=false)
    private Employee employee;

    @Column(name="name")
    private String employeeName;

    @Column(name="month")
    private Integer month;

    @Column(name="presents")
    private Integer employeePresentDays;

    @Column(name="absents")
    private Integer employeeAbsentDays;

    @Column(name="half_days")
    private Integer employeeHalfDays;

    @Column(name="late_days")
    private Integer employeeLateDays;
}
