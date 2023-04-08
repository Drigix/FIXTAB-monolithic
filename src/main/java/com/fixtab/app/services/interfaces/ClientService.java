package com.fixtab.app.services.interfaces;

import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.requests.EditClientRequest;
import com.fixtab.app.models.responses.ClientResponse;

import java.util.List;

public interface ClientService {

    List<ClientModel> getAllClients();

    List<ClientResponse> getAllNotDeletedClients();

    ClientResponse createClient(ClientRequest clientRequest);

    void editClient(EditClientRequest clientRequest);

    void deleteClient(Integer clientId);
}
