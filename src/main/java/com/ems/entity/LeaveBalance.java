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

    private Integer sickLeave;
    private Float casualLeave;
    private Integer annualLeave=18;
    private Integer maternityLeave;
    private Integer parentalLeave;
}
