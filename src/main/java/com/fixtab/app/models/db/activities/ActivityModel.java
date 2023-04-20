package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import com.fixtab.app.models.db.BaseEntity;
import com.fixtab.app.models.db.employees.EmployeeModel;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activities")
@Entity
public class ActivityModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activityid")
    private int activityId;

    @Column(name = "sequencenumber")
    private int sequenceNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "cancelled")
    private boolean cancelled;

    @Column(name = "status")
    private int status;

    @Column(name = "createdate")
    private Date createDate;

    @Column(name = "statusupatedate")
    private Date statusUpateDate;

     /*
    * FOREIGN KEYS !!!
    * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activitytypeid")
    private ActivityTypeModel activityType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeid")
    private EmployeeModel employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resultid")
    private ResultDictionaryModel result;
}