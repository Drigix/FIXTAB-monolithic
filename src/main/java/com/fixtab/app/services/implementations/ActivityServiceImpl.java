package com.fixtab.app.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fixtab.app.mappers.ActivityMapper;
import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.respositories.ActivityRepository;
import com.fixtab.app.services.interfaces.ActivityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{
    
    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;

    @Override
    public List<ActivityModel> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public List<ActivityResponse> getAllNotDeletedActivities() {
        List<ActivityModel> activityResponses = activityRepository.findAllByDeletedFalse();
        return activityResponses.stream().map(activityMapper::toResponse).collect(Collectors.toList());
    }
}
