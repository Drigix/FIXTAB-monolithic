package com.fixtab.app.services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.fixtab.app.respositories.ActivityRepository;
import com.fixtab.app.respositories.EmployeeRepository;
import com.fixtab.app.respositories.EmployeeRoleRepository;
import com.fixtab.app.respositories.RequestRepairRepository;
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
            activityResponses = activityRepository.findAllByDeletedFalseAndEmployee(employeeModel.get());
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
        RequestModel requestModel = requestRepairRepository.findOneByActivity(activityModel.get());
        newActivityModel.setCreatedDate(activityModel.get().getCreatedDate());
        newActivityModel.setCreatedBy(activityModel.get().getCreatedBy());
        newActivityModel.setDeleted(false);
        newActivityModel.setStatusUpateDate(new Date());
        if(newActivityModel.getResult().getName().equals("CANCEL")) {

        } else if (newActivityModel.getResult().getName().equals("FINISH")) {

        }
        activityRepository.save(newActivityModel);
    }
}
