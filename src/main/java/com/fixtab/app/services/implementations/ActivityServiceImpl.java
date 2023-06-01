package com.fixtab.app.services.implementations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fixtab.app.models.db.activities.StatusDictionaryModel;
import com.fixtab.app.respositories.*;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fixtab.app.mappers.ActivityMapper;
import com.fixtab.app.mappers.EmployeeMapper;
import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import com.fixtab.app.models.requests.ActivityRequest;
import com.fixtab.app.models.requests.EditActivityRequest;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.models.responses.EmployeeResponse;
import com.fixtab.app.security.AuthoritiesConstants;
import com.fixtab.app.services.interfaces.ActivityService;
import static com.fixtab.app.security.AuthoritiesConstants.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{
    
    private final ActivityRepository activityRepository;

    private final RequestRepairRepository requestRepairRepository;

    private final EmployeeRepository employeeRepository;

    private final EmployeeRoleRepository employeeRoleRepository;

    private final StatusDictionaryRepository statusDictionaryRepository;

    private final ActivityMapper activityMapper;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<ActivityModel> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public List<ActivityResponse> getAllNotDeletedActivities() {
        List<ActivityModel> activityResponses = activityRepository.findAllByDeletedFalse();
        return activityResponses.stream().map(activityMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponse> getAllNotDeletedActivitiesForEmployee() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<EmployeeModel> employeeModel = employeeRepository.findByEmail(email);
        Optional<EmployeeRoleModel> role = employeeRoleRepository.findById(employeeModel.get().getRoleId());
        List<ActivityModel> activityResponses = new ArrayList<ActivityModel>();
        if(AuthoritiesConstants.ADMIN.equals(role.get().getAuthority()) || AuthoritiesConstants.MANAGER.equals(role.get().getAuthority())) {
            activityResponses = activityRepository.findAllByDeletedFalse();
        } else {
            activityResponses = activityRepository.findAllByDeletedFalseAndEmployeeAndStatusNotNull(employeeModel.get());
        }
        return activityResponses.stream().map(activityMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getActivityManager(Integer activityId) {
        Optional<ActivityModel> activityModel = activityRepository.findById(activityId);
        RequestModel requestModel = requestRepairRepository.findOneByActivity(activityModel.get());
        return employeeMapper.toResponse(requestModel.getManager());
    }
    
    @Override
    public void editActivity(EditActivityRequest editActivityRequest) {
        ActivityModel newActivityModel = activityMapper.toEntity(editActivityRequest);
        Optional<ActivityModel> activityModel = activityRepository.findById(newActivityModel.getActivityId());
        Optional<EmployeeModel> employeeModel = employeeRepository.findById(editActivityRequest.getEmployee().getEmployeeId());
        RequestModel requestModel = requestRepairRepository.findOneByActivity(activityModel.get());
        newActivityModel.setCreatedDate(activityModel.get().getCreatedDate());
        newActivityModel.setCreatedBy(activityModel.get().getCreatedBy());
        newActivityModel.setDeleted(false);
        newActivityModel.setStatusUpateDate(new Date());
        newActivityModel.setEmployee(employeeModel.get());
        if(newActivityModel.getStatus().getName().equals("CANCEL")) {

        } else if (newActivityModel.getStatus().getName().equals("FINISH")) {
            List<ActivityModel> sortedActivities = requestModel.getActivity().stream().
                                                   sorted(Comparator.comparingInt(ActivityModel::getSequenceNumber)).
                                                   collect(Collectors.toList());
            if(newActivityModel.getSequenceNumber() == sortedActivities.get(requestModel.getActivity().size() - 1).getSequenceNumber()) {
                StatusDictionaryModel statusProgress = statusDictionaryRepository.findOneByName("FINISH");
                requestModel.setStatus(statusProgress);
                requestModel.setEndDate(new Date());
            } else {
                StatusDictionaryModel statusProgress = statusDictionaryRepository.findOneByName("OPEN");
                Optional<ActivityModel> nextActivity = requestModel.getActivity().stream().
                                        filter( a -> a.getSequenceNumber() == newActivityModel.getSequenceNumber() + 1).findFirst();
                nextActivity.get().setStatus(statusProgress);
                activityRepository.save(nextActivity.get());
            }
        } else if (newActivityModel.getStatus().getName().equals("PROGRESS")) {
            if(newActivityModel.getSequenceNumber() == 1) {
                StatusDictionaryModel statusProgress = statusDictionaryRepository.findOneByName("PROGRESS");
                requestModel.setStatus(statusProgress);
                requestModel.setProgressDate(new Date());
            }      
        }
        activityRepository.save(newActivityModel);
    }
}
