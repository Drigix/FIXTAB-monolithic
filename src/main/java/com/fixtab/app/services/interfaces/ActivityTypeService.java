package com.fixtab.app.services.interfaces;

import java.util.List;

import com.fixtab.app.models.db.activities.ActivityTypeModel;

public interface ActivityTypeService {
    
    List<ActivityTypeModel> getAll();

    ActivityTypeModel createActivityType(ActivityTypeModel activityTypeModel);
}
