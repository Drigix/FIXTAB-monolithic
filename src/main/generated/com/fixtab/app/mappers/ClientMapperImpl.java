package com.fixtab.app.mappers;

import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.requests.EditClientRequest;
import com.fixtab.app.models.responses.ClientResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-15T20:28:27+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientResponse toResponse(ClientModel clientModel) {
        if ( clientModel == null ) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setClientId( clientModel.getClientId() );
        clientResponse.setName( clientModel.getName() );
        clientResponse.setSurname( clientModel.getSurname() );
        clientResponse.setPhoneNumber( clientModel.getPhoneNumber() );
        clientResponse.setEmail( clientModel.getEmail() );
        clientResponse.setBirthDate( clientModel.getBirthDate() );
        clientResponse.setGender( clientModel.getGender() );
        clientResponse.setPesel( clientModel.getPesel() );
        clientResponse.setAddress( clientModel.getAddress() );

        clientResponse.setFullName( clientModel.getName() + " " + clientModel.getSurname() );

        return clientResponse;
    }

    @Override
    public ClientModel toEntity(ClientRequest clientRequest) {
        if ( clientRequest == null ) {
            return null;
        }

        ClientModel.ClientModelBuilder<?, ?> clientModel = ClientModel.builder();

        clientModel.name( clientRequest.getName() );
        clientModel.surname( clientRequest.getSurname() );
        clientModel.phoneNumber( clientRequest.getPhoneNumber() );
        clientModel.email( clientRequest.getEmail() );
        clientModel.birthDate( clientRequest.getBirthDate() );
        clientModel.gender( clientRequest.getGender() );
        clientModel.pesel( clientRequest.getPesel() );
        clientModel.address( clientRequest.getAddress() );

        return clientModel.build();
    }

    @Override
    public ClientModel toEntity(EditClientRequest editClientRequest) {
        if ( editClientRequest == null ) {
            return null;
        }

        ClientModel.ClientModelBuilder<?, ?> clientModel = ClientModel.builder();

        if ( editClientRequest.getClientId() != null ) {
            clientModel.clientId( editClientRequest.getClientId() );
        }
        clientModel.name( editClientRequest.getName() );
        clientModel.surname( editClientRequest.getSurname() );
        clientModel.phoneNumber( editClientRequest.getPhoneNumber() );
        clientModel.email( editClientRequest.getEmail() );
        clientModel.birthDate( editClientRequest.getBirthDate() );
        clientModel.gender( editClientRequest.getGender() );
        clientModel.pesel( editClientRequest.getPesel() );
        clientModel.address( editClientRequest.getAddress() );

        return clientModel.build();
    }
}
