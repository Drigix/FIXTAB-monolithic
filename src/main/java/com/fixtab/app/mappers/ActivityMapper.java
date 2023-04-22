package com.fixtab.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.requests.ActivityRequest;
import com.fixtab.app.models.responses.ActivityResponse;

@Mapper(componentModel = "spring", uses = {})
public interface ActivityMapper {

    ActivityResponse toResponse(ActivityModel activityModel);

    @Mapping(source = "employeeId", target = "employee.employeeId")
    ActivityModel toEntity(ActivityRequest activityRequest);

    //ActivityModel toEntity(EditClientRequest editClientRequest);

    default ActivityModel fromId (Integer id) {
        if (id == null) {
            return null;
        }
        ActivityModel activity = ActivityModel.builder().build();
        ActivityModel.builder().activityId(id);
        return activity;
    }
}
