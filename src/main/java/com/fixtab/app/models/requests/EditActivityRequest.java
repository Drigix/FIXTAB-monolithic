package com.fixtab.app.models.requests;

import java.util.Date;

import com.fixtab.app.models.db.activities.ActivityTypeModel;
import com.fixtab.app.models.db.activities.ResultDictionaryModel;
import com.fixtab.app.models.db.employees.EmployeeModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EditActivityRequest {

    private Integer activityId;

    private Integer sequenceNumber;

    private String description;

    private boolean cancelled;

    private Date createDate;

    private ActivityTypeModel activityType;

    private EmployeeModel employee;

    private ResultDictionaryModel result;
}
