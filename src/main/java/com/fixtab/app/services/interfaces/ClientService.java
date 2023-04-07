package com.fixtab.app.services.interfaces;

import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.responses.ClientResponse;

import java.util.List;

public interface ClientService {

    List<ClientResponse> getAllClients();

    List<ClientResponse> getAllNotDeletedClients();

    ClientResponse createClient(ClientRequest clientRequest);

    void deleteClient(Integer clientId);
}
