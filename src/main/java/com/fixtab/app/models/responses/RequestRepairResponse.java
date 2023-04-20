package com.fixtab.app.models.responses;

import java.util.Date;
import java.util.List;

import com.fixtab.app.models.db.activities.ResultDictionaryModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestRepairResponse {

    private Integer requestId;

    private String description;

    private Integer status;

    private boolean requestCancelled;

    private Date openDate;

    private Date progressDate;

    private Date endDate;

    private TargetObjectModel targetObject;

    private EmployeeModel manager;

    private ResultDictionaryModel result;

    private List<ActivityResponse> activity;
}
