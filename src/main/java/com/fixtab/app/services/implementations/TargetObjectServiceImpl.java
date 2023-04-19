package com.fixtab.app.services.implementations;

import com.fixtab.app.exceptions.ItemNoLongerExistsException;
import com.fixtab.app.models.requests.EditTargetObjectRequest;
import com.fixtab.app.respositories.ClientRepository;
import com.fixtab.app.mappers.TargetObjectMapper;
import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.requests.TargetObjectRequest;
import com.fixtab.app.models.responses.TargetObjectResponse;
import com.fixtab.app.respositories.TargetObjectRepository;
import com.fixtab.app.services.interfaces.TargetObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @Override
    public List<TargetObjectModel> getAllTargetObjects(){
        return targetObjectRepository.findAll();
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
        if(client.isEmpty())
            throw new ItemNoLongerExistsException();
        targetObject.setClient(client.get());
        targetObject.setDeleted(false);
        targetObjectRepository.save(targetObject);
        return targetObjectMapper.toResponse(targetObject);
    }

    @Override
    public void editTargetObject(EditTargetObjectRequest editTargetObjectRequest){
        TargetObjectModel updateTargetObjectModel = targetObjectMapper.toEntity(editTargetObjectRequest);
        Optional<TargetObjectModel> targetObjectModel = targetObjectRepository.findById(updateTargetObjectModel.getTargetId());
        if (targetObjectModel.isEmpty())
            throw new ItemNoLongerExistsException();
        String editBy = SecurityContextHolder.getContext().getAuthentication().getName();
        updateTargetObjectModel.setDeleted(targetObjectModel.get().getDeleted());
        updateTargetObjectModel.setCreatedBy(targetObjectModel.get().getCreatedBy());
        updateTargetObjectModel.setCreatedDate(targetObjectModel.get().getCreatedDate());
        updateTargetObjectModel.setModifiedBy(editBy);

        targetObjectRepository.save(updateTargetObjectModel);
    }

    @Override
    public void deleteTargetObject(Integer targetId){
        Optional<TargetObjectModel> targetObject = targetObjectRepository.findById(targetId);
        if(targetObject.isEmpty())
            throw new ItemNoLongerExistsException();
        targetObject.get().setDeleted(true);
        targetObjectRepository.save(targetObject.get());
    }

}
