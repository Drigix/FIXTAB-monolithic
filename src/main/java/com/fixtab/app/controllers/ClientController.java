package com.fixtab.app.controllers;

import com.fixtab.app.config.HeadersConfig;
import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.requests.EditClientRequest;
import com.fixtab.app.models.responses.ClientResponse;
import static com.fixtab.app.security.AuthoritiesConstants.*;
import com.fixtab.app.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;

    private final String applicationName = "FIXTAB clientManager: ";

    @GetMapping("getAllClients")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public List<ClientModel> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("getAllNotDeletedClients")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public ResponseEntity<List<ClientResponse>> getAllNotDeletedClients() throws URISyntaxException {
        List<ClientResponse> clientResponse = clientService.getAllNotDeletedClients();
        HeadersConfig headers = new HeadersConfig(applicationName, "Get all not deleted Clients");
        return ResponseEntity
                .created(new URI("/api/client" + "getAllNotDeletedClients"))
                .headers(headers.getHeaders())
                .body(clientResponse);
    }

    @PostMapping("createClient")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest) throws URISyntaxException, UnsupportedEncodingException {
        ClientResponse clientResponse = clientService.createClient(clientRequest);
        HeadersConfig headers = new HeadersConfig(applicationName, String.valueOf(clientResponse.getClientId()));
        return ResponseEntity
                .created(new URI("/api/client" + clientResponse.getClientId()))
                .headers(headers.getHeaders())
                .body(clientResponse);
    }

    @PutMapping("editClient")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public ResponseEntity<?> editClient(@RequestBody EditClientRequest editClientRequest) throws URISyntaxException {
        clientService.editClient(editClientRequest);
        HeadersConfig headers = new HeadersConfig(applicationName, "clientDeleted");
        return ResponseEntity
                .created(new URI("/api/client" + "editClient/" + editClientRequest.getClientId()))
                .headers(headers.getHeaders())
                .body("\"Client with id " + editClientRequest.getClientId() + " has been updated.\"");
    }

    @DeleteMapping("deleteClient/{clientId}")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) throws URISyntaxException {
        clientService.deleteClient(clientId);
        HeadersConfig headers = new HeadersConfig(applicationName, "clientDeleted");
        return ResponseEntity
                .created(new URI("/api/client" + "deleteClient/" + clientId))
                .headers(headers.getHeaders())
                .body("\"Client with id " + clientId + " has been deleted.\"");
    }
}
