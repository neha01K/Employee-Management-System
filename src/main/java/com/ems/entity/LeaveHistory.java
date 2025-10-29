package com.ems.entity;

import com.ems.enums.LeaveStatus;
import com.ems.enums.LeaveType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LeaveHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(referencedColumnName="employee_id", name="employee_id", nullable=false, unique=true)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name="leave_type")
    private LeaveType leaveType;

    @Column(name="start_date")
    private LocalDate leaveStartDate;

    @Column(name="end_date")
    private LocalDate leaveEndDate;

    @Column(name="reason")
    private String leaveReason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;

    @Column(name="applied_date")
    private LocalDate appliedDate;
}
