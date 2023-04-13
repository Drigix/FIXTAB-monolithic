package com.fixtab.app.mappers;

import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.requests.EditTargetObjectRequest;
import com.fixtab.app.models.requests.TargetObjectRequest;
import com.fixtab.app.models.responses.TargetObjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface TargetObjectMapper {

    TargetObjectResponse toResponse(TargetObjectModel targetObjectModel);

    @Mapping(source = "clientId", target = "client.clientId")
    TargetObjectModel toEntity(TargetObjectRequest targetObjectRequest);

    @Mapping(source = "clientId", target = "client.clientId")
    TargetObjectModel toEntity(EditTargetObjectRequest editTargetObjectRequest);


    default TargetObjectModel fromId (Integer id) {
        if (id == null) {
            return null;
        }
        TargetObjectModel targetObject = TargetObjectModel.builder().build();
        TargetObjectModel.builder().targetId(id);
        return targetObject;
    }
}

