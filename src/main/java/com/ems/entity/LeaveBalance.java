package com.ems.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "employee_id", name="employee_id", nullable=false, unique=true)
    private Employee employee;

    private Integer sickLeave=12;
    private Integer casualLeave=15;
    private Integer annualLeave=18;
    private Integer maternityLeave=2;
    private Integer parentalLeave=2;
}
