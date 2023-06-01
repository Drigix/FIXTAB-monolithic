package com.fixtab.app.mappers;

import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.requests.EditTargetObjectRequest;
import com.fixtab.app.models.requests.TargetObjectRequest;
import com.fixtab.app.models.responses.TargetObjectResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T21:42:59+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class TargetObjectMapperImpl implements TargetObjectMapper {

    @Override
    public TargetObjectResponse toResponse(TargetObjectModel targetObjectModel) {
        if ( targetObjectModel == null ) {
            return null;
        }

        TargetObjectResponse.TargetObjectResponseBuilder targetObjectResponse = TargetObjectResponse.builder();

        targetObjectResponse.targetId( targetObjectModel.getTargetId() );
        targetObjectResponse.name( targetObjectModel.getName() );
        targetObjectResponse.client( targetObjectModel.getClient() );
        targetObjectResponse.objectType( targetObjectModel.getObjectType() );

        return targetObjectResponse.build();
    }

    @Override
    public TargetObjectModel toEntity(TargetObjectRequest targetObjectRequest) {
        if ( targetObjectRequest == null ) {
            return null;
        }

        TargetObjectModel.TargetObjectModelBuilder<?, ?> targetObjectModel = TargetObjectModel.builder();

        targetObjectModel.client( targetObjectRequestToClientModel( targetObjectRequest ) );
        targetObjectModel.name( targetObjectRequest.getName() );
        targetObjectModel.objectType( targetObjectRequest.getObjectType() );

        return targetObjectModel.build();
    }

    @Override
    public TargetObjectModel toEntity(EditTargetObjectRequest editTargetObjectRequest) {
        if ( editTargetObjectRequest == null ) {
            return null;
        }

        TargetObjectModel.TargetObjectModelBuilder<?, ?> targetObjectModel = TargetObjectModel.builder();

        targetObjectModel.client( editTargetObjectRequestToClientModel( editTargetObjectRequest ) );
        if ( editTargetObjectRequest.getTargetId() != null ) {
            targetObjectModel.targetId( editTargetObjectRequest.getTargetId() );
        }
        targetObjectModel.name( editTargetObjectRequest.getName() );
        targetObjectModel.objectType( editTargetObjectRequest.getObjectType() );

        return targetObjectModel.build();
    }

    protected ClientModel targetObjectRequestToClientModel(TargetObjectRequest targetObjectRequest) {
        if ( targetObjectRequest == null ) {
            return null;
        }

        ClientModel.ClientModelBuilder<?, ?> clientModel = ClientModel.builder();

        if ( targetObjectRequest.getClientId() != null ) {
            clientModel.clientId( targetObjectRequest.getClientId() );
        }

        return clientModel.build();
    }

    protected ClientModel editTargetObjectRequestToClientModel(EditTargetObjectRequest editTargetObjectRequest) {
        if ( editTargetObjectRequest == null ) {
            return null;
        }

        ClientModel.ClientModelBuilder<?, ?> clientModel = ClientModel.builder();

        if ( editTargetObjectRequest.getClientId() != null ) {
            clientModel.clientId( editTargetObjectRequest.getClientId() );
        }

        return clientModel.build();
    }
}
