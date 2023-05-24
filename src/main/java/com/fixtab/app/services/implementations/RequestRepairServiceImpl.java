package com.fixtab.app.services.implementations;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.activities.ResultDictionaryModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.respositories.EmployeeRepository;
import com.fixtab.app.respositories.TargetObjectRepository;
import org.springframework.stereotype.Service;

import com.fixtab.app.mappers.ActivityMapper;
import com.fixtab.app.mappers.RequestRepairMapper;
import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.requests.EditRequestRepairRequest;
import com.fixtab.app.models.requests.RequestRepairRequest;
import com.fixtab.app.models.responses.RequestRepairResponse;
import com.fixtab.app.respositories.RequestRepairRepository;
import com.fixtab.app.respositories.ResultDictionaryRepository;
import com.fixtab.app.services.interfaces.RequestRepairService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestRepairServiceImpl implements RequestRepairService{

    private final RequestRepairRepository requestRepairRepository;

    private final ResultDictionaryRepository resultDictionaryRepository;

    private final EmployeeRepository employeeRepository;

    private final TargetObjectRepository targetObjectRepository;

    private final RequestRepairMapper requestRepairMapper;

    private final ActivityMapper activityMapper;
    
    @Override
    public List<RequestModel> getAllRequestRepairs() {  
        return requestRepairRepository.findAll();
    }
    
    @Override
    public List<RequestRepairResponse> getAllNotDeletedRequestRepairs() {
        List<RequestModel> requests = requestRepairRepository.findAllByDeletedFalse();
        for(RequestModel request: requests) {
            request.setActivity(request.getActivity().stream().
            sorted(Comparator.comparingInt(ActivityModel::getSequenceNumber)).
            collect(Collectors.toList()));
        }
        return requests.stream().map(requestRepairMapper::toResponse).collect(Collectors.toList());
    }
    
    @Override
    public void createRequestRepair(RequestRepairRequest requestRepairRequest) {
        RequestModel requestModel = requestRepairMapper.toEntity(requestRepairRequest);
        List<ActivityModel> activities = requestRepairRequest.getActivities().stream().map(activityMapper::toEntity).collect(Collectors.toList());
        Optional<EmployeeModel> manager = employeeRepository.findById(requestModel.getManager().getEmployeeId());
        Optional<TargetObjectModel> targetObject = targetObjectRepository.findById(requestModel.getTargetObject().getTargetId());
        ResultDictionaryModel resultDictionaryModel = resultDictionaryRepository.findOneByName("OPEN");
        requestModel.setActivity(activities);
        requestModel.setManager(manager.get());
        requestModel.setTargetObject(targetObject.get());
        requestModel.setDeleted(false);
        requestModel.setOpenDate(new Date());
        requestModel.setResult(resultDictionaryModel);
        for(ActivityModel activity: requestModel.getActivity()) {
            Optional<EmployeeModel> employee = employeeRepository.findById(activity.getEmployee().getEmployeeId());
            activity.setEmployee(employee.get());
            activity.setDeleted(false);
            activity.setCreateDate(new Date());
            activity.setStatusUpateDate(new Date());
            activity.setResult(resultDictionaryModel);
        }
        requestRepairRepository.save(requestModel);
    }

    @Override
    public void editRequestRepair(EditRequestRepairRequest editRequestRepairRequest) {
        RequestModel newRequestModel = requestRepairMapper.toEntity(editRequestRepairRequest);
        Optional<RequestModel> requestModel = requestRepairRepository.findById(editRequestRepairRequest.getRequestId());
        Optional<TargetObjectModel> newTargetObject = targetObjectRepository.findById(newRequestModel.getTargetObject().getTargetId());
        mapOldRequestRepairToNewRequestRepair(newRequestModel, requestModel.get(), newTargetObject.get());
        requestRepairRepository.save(newRequestModel);
    }

    public void mapOldRequestRepairToNewRequestRepair(RequestModel newRequestModel, RequestModel requestModel, TargetObjectModel newTargetObject) {
        newRequestModel.setActivity(requestModel.getActivity());
        newRequestModel.setManager(requestModel.getManager());
        newRequestModel.setCreatedDate(requestModel.getCreatedDate());
        newRequestModel.setCreatedBy(requestModel.getCreatedBy());
        newRequestModel.setDeleted(false);
        newRequestModel.getTargetObject().setCreatedDate(newTargetObject.getCreatedDate());
        newRequestModel.getTargetObject().setCreatedBy(newTargetObject.getCreatedBy());
        newRequestModel.getTargetObject().setDeleted(false);
        checkResult(newRequestModel, requestModel);
    }

    public void checkResult(RequestModel newRequestModel, RequestModel requestModel) {
        if(newRequestModel.getResult().getName().equals("CANCEL")) {
            ResultDictionaryModel resultCancel = resultDictionaryRepository.findOneByName("CANCEL"); 
            newRequestModel.setResult(resultCancel);
            newRequestModel.setRequestCancelled(true);
            newRequestModel.setProgressDate(requestModel.getProgressDate());
            newRequestModel.setEndDate(new Date());
            for(ActivityModel activity: newRequestModel.getActivity()) {
                activity.setResult(resultCancel);
                activity.setStatusUpateDate(new Date());
            }
        } else {
            newRequestModel.setRequestCancelled(false);
            if(newRequestModel.getResult().getName().equals("PROGRESS")) {
                newRequestModel.setProgressDate(new Date());
                newRequestModel.setEndDate(null);
                if(requestModel.getResult().getName().equals("CANCEL")) {
                    ResultDictionaryModel resultProgress = resultDictionaryRepository.findOneByName("PROGRESS"); 
                    for(ActivityModel activity: newRequestModel.getActivity()) {
                        activity.setResult(resultProgress);
                        activity.setStatusUpateDate(new Date());
                    }
                }
            } else if(newRequestModel.getResult().getName().equals("FINISH")) {
                newRequestModel.setProgressDate(requestModel.getProgressDate());
                newRequestModel.setEndDate(new Date());
            }
        }
    }
}
