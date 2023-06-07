package com.fixtab.app.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fixtab.app.models.db.activities.ActivityTypeModel;
import com.fixtab.app.respositories.ActivityTypeRepository;
import com.fixtab.app.services.interfaces.ActivityTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityTypeImpl implements ActivityTypeService {

    private final ActivityTypeRepository activityTypeRepository;
    
    @Override
    public List<ActivityTypeModel> getAll() {
        return activityTypeRepository.findAll();
    }

    @Override
    public ActivityTypeModel createActivityType(ActivityTypeModel activityTypeModel) {
        ActivityTypeModel saveActivityTypeModel = activityTypeRepository.save(activityTypeModel);
        return saveActivityTypeModel;
    }
}
