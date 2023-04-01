package com.fixtab.app.models.db.activities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activitytype")
@Entity
public class ActivityTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeid")
    private int typeId;
    @Column(name = "typename")
    private String typeName;
}
