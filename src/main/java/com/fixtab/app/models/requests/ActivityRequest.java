package com.fixtab.app.models.requests;

import com.fixtab.app.models.db.activities.ActivityTypeModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ActivityRequest {

    private Integer sequenceNumber;

    private String description;

    private ActivityTypeModel activityType;

    private Integer employeeId;
}
