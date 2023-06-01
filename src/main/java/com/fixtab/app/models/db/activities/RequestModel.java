package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

import com.fixtab.app.models.db.BaseEntity;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.db.employees.EmployeeModel;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "requests")
@Entity
public class RequestModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestid")
    private int requestId;

    @Column(name = "description")
    private String description;

    @Column(name = "result")
    private String result;

    @Column(name = "requestcancelled")
    private boolean requestCancelled;

    @Column(name = "opendate")
    private Date openDate;

    @Column(name = "progressdate")
    private Date progressDate;

    @Column(name = "enddate")
    private Date endDate;

    /*
    * FOREIGN KEYS !!!
    * */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "targetobjectid")
    private TargetObjectModel targetObject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managerid")
    private EmployeeModel manager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statusid")
    private StatusDictionaryModel status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "requestid")    
    private List<ActivityModel> activity;
}
