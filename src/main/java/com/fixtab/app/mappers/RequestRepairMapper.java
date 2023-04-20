package com.fixtab.app.mappers;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.requests.ActivityRequest;
import org.mapstruct.Mapper;

import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.requests.RequestRepairRequest;
import com.fixtab.app.models.responses.RequestRepairResponse;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {})
public interface RequestRepairMapper {
    
    RequestRepairResponse toResponse(RequestModel requestModel);

    @Mapping(source = "managerId", target = "manager.employeeId")
    @Mapping(source = "targetObjectId", target = "targetObject.targetId")
    @Mapping(source = "activities", target = "activity")
    RequestModel toEntity(RequestRepairRequest requestRepairRequest);

    //EmployeeModel toEntity(EditEmployeeRequest employeeRequest);

    default RequestModel fromId (Integer id) {
        if (id == null) {
            return null;
        }
        RequestModel requestRepair = RequestModel.builder().build();
        RequestModel.builder().requestId(id);
        return requestRepair;
    }

    default List<ActivityModel> mapActivityRequestsToActivityModels(List<ActivityRequest> activities) {
        if (activities == null) {
            return null;
        }
        return activities.stream()
                .map(this::activityRequestToActivityModel)
                .collect(Collectors.toList());
    }

    ActivityModel activityRequestToActivityModel(ActivityRequest activity);

}
