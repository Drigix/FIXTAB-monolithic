package com.fixtab.app.models.db.customers;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "targetobject")
@Entity
public class TargetObjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "targetid")
    private int targetId;

    @Column(name = "name")
    private String name;

    @Column(name = "createdate")
    private Date createDate;

    @Column(name = "objecttypeid")
    private Integer objectTypeId;
}
