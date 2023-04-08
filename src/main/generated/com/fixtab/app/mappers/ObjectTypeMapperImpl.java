package com.fixtab.app.mappers;

import com.fixtab.app.models.db.customers.ObjectTypeModel;
import com.fixtab.app.models.requests.ObjectTypeRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-08T14:36:45+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class ObjectTypeMapperImpl implements ObjectTypeMapper {

    @Override
    public ObjectTypeModel toEntity(ObjectTypeRequest objectTypeRequest) {
        if ( objectTypeRequest == null ) {
            return null;
        }

        ObjectTypeModel.ObjectTypeModelBuilder objectTypeModel = ObjectTypeModel.builder();

        objectTypeModel.nameType( objectTypeRequest.getNameType() );

        return objectTypeModel.build();
    }
}
