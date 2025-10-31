package com.ems.entity;


import com.ems.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="serial_no")
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName="employee_id", name="employee_id")
    private Employee employee;

    @Column(name="name")
    private String employeeName;

    private LocalDate date;

    @Column(name="check_in_time")
    private LocalDateTime checkInTime;

    @Column(name="check_out_time")
    private LocalDateTime checkOutTime;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private AttendanceStatus attendanceStatus= AttendanceStatus.ABSENT;

    @Column(name="worked_hours")
    private Double workedHours;

}
