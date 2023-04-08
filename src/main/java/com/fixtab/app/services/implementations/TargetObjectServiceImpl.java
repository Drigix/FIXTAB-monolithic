package com.fixtab.app.services.implementations;

import com.fixtab.app.respositories.ClientRepository;
import com.fixtab.app.mappers.ObjectTypeMapper;
import com.fixtab.app.mappers.TargetObjectMapper;
import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.requests.TargetObjectRequest;
import com.fixtab.app.models.responses.TargetObjectResponse;
import com.fixtab.app.respositories.TargetObjectRepository;
import com.fixtab.app.services.interfaces.TargetObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TargetObjectServiceImpl implements TargetObjectService {

    private final TargetObjectRepository targetObjectRepository;

    private final ClientRepository clientRepository;

    private final TargetObjectMapper targetObjectMapper;

    private final ObjectTypeMapper objectTypeMapper;

    @Override
    public List<TargetObjectModel> getAllTargetObjects(){
        List<TargetObjectModel> targetObjects = targetObjectRepository.findAll();
        return targetObjects;
    }

    @Override
    public List<TargetObjectResponse> getAllNotDeletedTargetObjects(){
        List<TargetObjectModel> targetObjects = targetObjectRepository.findAllByDeletedFalse();
        return targetObjects.stream().map(targetObjectMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public TargetObjectResponse createTargetObject(TargetObjectRequest targetObjectRequest){
        TargetObjectModel targetObject = targetObjectMapper.toEntity(targetObjectRequest);
        Optional<ClientModel> client = clientRepository.findById(targetObject.getClient().getClientId());
        targetObject.setClient(client.get());
        targetObject.setDeleted(false);
        targetObjectRepository.save(targetObject);
        return targetObjectMapper.toResponse(targetObject);
    }

    @Override
    public void editTargetObject(TargetObjectRequest targetObjectRequest){

    }

    @Override
    public void deleteTargetObject(Integer targetId){
        Optional<TargetObjectModel> targetObject = targetObjectRepository.findById(targetId);
        targetObject.get().setDeleted(true);
        targetObjectRepository.save(targetObject.get());
    }

}
