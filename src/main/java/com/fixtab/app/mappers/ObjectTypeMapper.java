package com.fixtab.app.mappers;

import com.fixtab.app.models.db.customers.ObjectTypeModel;
import com.fixtab.app.models.requests.ObjectTypeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ObjectTypeMapper {

    ObjectTypeModel toEntity(ObjectTypeRequest objectTypeRequest);

    default ObjectTypeModel fromId (Integer id) {
        if (id == null) {
            return null;
        }
        ObjectTypeModel objectType = ObjectTypeModel.builder().build();
        ObjectTypeModel.builder().codeTypeId(id);
        return objectType;
    }
}
