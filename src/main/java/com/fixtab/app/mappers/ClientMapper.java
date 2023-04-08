package com.fixtab.app.mappers;

import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.requests.EditClientRequest;
import com.fixtab.app.models.responses.ClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ClientMapper {

    ClientResponse toResponse(ClientModel clientModel);

    ClientModel toEntity(ClientRequest clientRequest);

    ClientModel toEntity(EditClientRequest editClientRequest);

    default ClientModel fromId (Integer id) {
        if (id == null) {
            return null;
        }
        ClientModel client = ClientModel.builder().build();
        ClientModel.builder().clientId(id);
        return client;
    }
}
