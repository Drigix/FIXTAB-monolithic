package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "requests")
@Entity
public class RequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestid")
    private int requestId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private int status;

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
    @Column(name = "targetobjectid")
    private Integer targetObjectId;

    @Column(name = "managerid")
    private Integer managerId;
}
