package com.fixtab.app.controllers;

import com.fixtab.app.config.HeadersConfig;
import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.requests.EditClientRequest;
import com.fixtab.app.models.responses.ClientResponse;
import com.fixtab.app.security.AuthoritiesConstants;
import com.fixtab.app.services.interfaces.ClientService;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;

    private final String applicationName = "FIXTAB clientManager: ";

    @GetMapping("getAllClients")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public List<ClientModel> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("getAllNotDeletedClients")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public List<ClientResponse> getAllNotDeletedClients() {
        return clientService.getAllNotDeletedClients();
    }

    @PostMapping("createClient")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest) throws URISyntaxException, UnsupportedEncodingException {
        ClientResponse clientResponse = clientService.createClient(clientRequest);
        HeadersConfig headers = new HeadersConfig(applicationName, String.valueOf(clientResponse.getClientId()));
        return ResponseEntity
                .created(new URI("/api/client" + clientResponse.getClientId()))
                .headers(headers.getHeaders())
                .body(clientResponse);
    }

    @PutMapping("editClient")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public ResponseEntity<?> editClient(@RequestBody EditClientRequest editClientRequest) {
        clientService.editClient(editClientRequest);
        return new ResponseEntity<>("{\"Success\":\"Customer has been updated!\"}",HttpStatus.OK);
    }

    @DeleteMapping("deleteClient/{clientId}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.ADMIN + "\", \"" + AuthoritiesConstants.MANAGER + "\")")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
