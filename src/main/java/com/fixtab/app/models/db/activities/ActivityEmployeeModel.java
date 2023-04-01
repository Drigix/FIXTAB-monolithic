package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activityemployees")
@Entity
public class ActivityEmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
//
    @Column(name = "activityid")
    private Integer activityId;

    @Column(name = "employeeid")
    private Integer employeeId;
}
