package com.fixtab.app.services.implementations;

import com.fixtab.app.mappers.ClientMapper;
import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.responses.ClientResponse;
import com.fixtab.app.respositories.ClientRepository;
import com.fixtab.app.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Override
    public List<ClientResponse> getAllClients() {
        List<ClientModel> clients = clientRepository.findAll();
        return clients.stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> getAllNotDeletedClients() {
        List<ClientModel> clients = clientRepository.findAllByDeletedFalse();
        return clients.stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        ClientModel client = clientMapper.toEntity(clientRequest);
        client.setDeleted(false);
        clientRepository.save(client);
        return clientMapper.toResponse(client);
    }

    @Override
    public void deleteClient(Integer clientId) {
        Optional<ClientModel> client = clientRepository.findById(clientId);
        client.get().setDeleted(true);
        clientRepository.save(client.get());
    }

}
