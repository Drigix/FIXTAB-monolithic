package com.fixtab.app.mappers;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.requests.ActivityRequest;
import com.fixtab.app.models.requests.EditRequestRepairRequest;

import org.mapstruct.Mapper;

import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.requests.RequestRepairRequest;
import com.fixtab.app.models.responses.RequestRepairResponse;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface RequestRepairMapper {
    
    RequestRepairResponse toResponse(RequestModel requestModel);

    @Mapping(source = "managerId", target = "manager.employeeId")
    @Mapping(source = "targetObjectId", target = "targetObject.targetId")
    RequestModel toEntity(RequestRepairRequest requestRepairRequest);

    RequestModel toEntity(EditRequestRepairRequest editRequestRepairRequest);

    default RequestModel fromId (Integer id) {
        if (id == null) {
            return null;
        }
        RequestModel requestRepair = RequestModel.builder().build();
        RequestModel.builder().requestId(id);
        return requestRepair;
    }
}
