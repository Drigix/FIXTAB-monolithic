package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activities")
@Entity
public class ActivityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activityid")
    private int activityId;

    @Column(name = "sequencenumber")
    private int sequenceNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "ready")
    private boolean ready;

    @Column(name = "cancelled")
    private boolean cancelled;

    @Column(name = "status")
    private int status;

    @Column(name = "activitytypeid")
    private int activityTypeId;

    @Column(name = "createdate")
    private Date createDate;

    @Column(name = "statusupatedate")
    private Date statusUpateDate;

    @Column(name = "requestid")
    private int requestId;


}