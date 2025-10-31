package com.ems.entity;

import com.ems.enums.LeaveStatus;
import com.ems.enums.LeaveType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

    @Id
    private String leaveRequestID;

    @ManyToOne
    @JoinColumn(referencedColumnName="employee_id",name="employee_id",nullable=false,unique=true)
    private Employee employee;

    private LeaveType leaveType;

    @Column(name="start_date")
    private LocalDate leaveStartDate;

    @Column(name="end_date")
    private LocalDate leaveEndDate;

    @Column(name="number_of_days")
    private int numberOfDaysOfLeave;

    @Column(name="reason")
    private String leaveReason;
    private LeaveStatus leaveStatus;

    @Column(name="approved_by")
    private String leaveApprovedBy;

    @Column(name="application_date")
    private LocalDate leaveRequestDate;

}
