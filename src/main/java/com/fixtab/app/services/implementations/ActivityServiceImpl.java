package com.fixtab.app.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fixtab.app.mappers.ActivityMapper;
import com.fixtab.app.mappers.EmployeeMapper;
import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.models.responses.EmployeeResponse;
import com.fixtab.app.respositories.ActivityRepository;
import com.fixtab.app.respositories.RequestRepairRepository;
import com.fixtab.app.services.interfaces.ActivityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{
    
    private final ActivityRepository activityRepository;

    private final RequestRepairRepository requestRepairRepository;

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
    public EmployeeResponse getActivityManager(Integer activityId) {
        Optional<ActivityModel> activityModel = activityRepository.findById(activityId);
        RequestModel requestModel = requestRepairRepository.findOneByActivity(activityModel.get());
        return employeeMapper.toResponse(requestModel.getManager());
    } 
}
