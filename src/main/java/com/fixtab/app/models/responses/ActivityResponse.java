package com.fixtab.app.models.responses;

import java.util.Date;

import com.fixtab.app.models.db.activities.ActivityTypeModel;
import com.fixtab.app.models.db.activities.StatusDictionaryModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActivityResponse {
    
    private Integer activityId;

    private Integer sequenceNumber;

    private String description;

    private boolean cancelled;

    private String result;

    private Date createDate;

    private Date statusUpateDate;

    private ActivityTypeModel activityType;

    private EmployeeResponse employee;

    private StatusDictionaryModel status;
}
