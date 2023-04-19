package com.fixtab.app.services.implementations;

import com.fixtab.app.exceptions.EmailAlreadyExistsException;
import com.fixtab.app.exceptions.ItemNoLongerExistsException;
import com.fixtab.app.mappers.ClientMapper;
import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.requests.ClientRequest;
import com.fixtab.app.models.requests.EditClientRequest;
import com.fixtab.app.models.responses.ClientResponse;
import com.fixtab.app.respositories.ClientRepository;
import com.fixtab.app.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<ClientResponse> getAllNotDeletedClients() {
        List<ClientModel> clients = clientRepository.findAllByDeletedFalse();
        return clients.stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        Optional<ClientModel> optionalClientModel = clientRepository.findByEmail(clientRequest.getEmail());
        if (optionalClientModel.isPresent())
            throw new EmailAlreadyExistsException();
        ClientModel client = clientMapper.toEntity(clientRequest);
        client.setDeleted(false);
        clientRepository.save(client);
        return clientMapper.toResponse(client);
    }

    @Override
    public void editClient(EditClientRequest editClientRequest) {
        ClientModel updateClient = clientMapper.toEntity(editClientRequest);
        Optional<ClientModel> client = clientRepository.findById(editClientRequest.getClientId());
        if(client.isEmpty())
            throw new ItemNoLongerExistsException();
        String editBy = SecurityContextHolder.getContext().getAuthentication().getName();
        updateClient.setDeleted(client.get().getDeleted());
        updateClient.setCreatedBy(client.get().getCreatedBy());
        updateClient.setCreatedDate(client.get().getCreatedDate());
        updateClient.setModifiedBy(editBy);
        clientRepository.save(updateClient);
    }

    @Override
    public void deleteClient(Integer clientId) {
        Optional<ClientModel> client = clientRepository.findById(clientId);
        if(client.isEmpty())
            throw new ItemNoLongerExistsException();
        client.get().setDeleted(true);
        clientRepository.save(client.get());
    }

}
