package com.fixtab.app.controllers;

import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.requests.EditClientRequest;
import com.fixtab.app.models.responses.ClientResponse;
import com.fixtab.app.services.interfaces.ClientService;
import com.fixtab.app.services.interfaces.EmployeeRoleService;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("getAllClients")
    public List<ClientModel> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("getAllNotDeletedClients")
    public List<ClientResponse> getAllNotDeletedClients() {
        return clientService.getAllNotDeletedClients();
    }

    @PostMapping("createClient")
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest) {
        clientService.createClient(clientRequest);
        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

    @PutMapping("editClient")
    public ResponseEntity<?> editClient(@RequestBody EditClientRequest editClientRequest) {
        clientService.editClient(editClientRequest);
        return new ResponseEntity<>("{\"Success\":\"Customer has been updated!\"}",HttpStatus.OK);
    }


    @DeleteMapping("deleteClient/{clientId}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
