package com.fixtab.app.models.requests;

import java.util.Date;

import com.fixtab.app.models.db.activities.ActivityTypeModel;
import com.fixtab.app.models.db.activities.StatusDictionaryModel;
import com.fixtab.app.models.db.employees.EmployeeModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EditActivityRequest {

    private Integer activityId;

    private Integer sequenceNumber;

    private String description;

    private String result;

    private boolean cancelled;

    private Date createDate;

    private ActivityTypeModel activityType;

    private EmployeeModel employee;

    private StatusDictionaryModel status;
}
