package com.fixtab.app.services.interfaces;

import java.util.List;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.requests.EditActivityRequest;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.models.responses.EmployeeResponse;

public interface ActivityService {
    
    List<ActivityModel> getAllActivities();

    List<ActivityResponse> getAllNotDeletedActivities();

    List<ActivityResponse> getAllNotDeletedActivitiesForEmployee();

    EmployeeResponse getActivityManager(Integer activityId);

    void editActivity(EditActivityRequest editActivityRequest);
}
