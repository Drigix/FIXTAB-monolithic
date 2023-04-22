package com.fixtab.app.services.interfaces;

import java.util.List;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.responses.ActivityResponse;

public interface ActivityService {
    
    List<ActivityModel> getAllActivities();

    List<ActivityResponse> getAllNotDeletedActivities();

}
