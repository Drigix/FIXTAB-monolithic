package com.fixtab.app.controllers;

import org.springframework.web.bind.annotation.*;

import com.fixtab.app.config.HeadersConfig;
import com.fixtab.app.models.requests.EditActivityRequest;
import com.fixtab.app.models.responses.ActivityResponse;
import com.fixtab.app.models.responses.EmployeeResponse;
import com.fixtab.app.services.interfaces.ActivityService;

import static com.fixtab.app.security.AuthoritiesConstants.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import lombok.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/activity")
public class ActivityController {
    
    private final ActivityService activityService;

    private final String applicationName = "FIXTAB acitivityManager: ";

    // @GetMapping("getAllNotDeletedActivities")
    // @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    // public ResponseEntity<List<ActivityModel>> getAllActivities() throws URISyntaxException {
    //     List<ActivityModel> activityResponse = activityService.getAllActivities();
    //     HeadersConfig headers = new HeadersConfig(applicationName, "Get all not deleted Activitys");
    //     return ResponseEntity
    //             .created(new URI("/api/activity" + "getAllNotDeletedActivitys"))
    //             .headers(headers.getHeaders())
    //             .body(activityResponse);
    // }

    @GetMapping("getAllNotDeletedActivities")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public ResponseEntity<List<ActivityResponse>> getAllNotDeletedActivities() throws URISyntaxException {
        List<ActivityResponse> activityResponse = activityService.getAllNotDeletedActivities();
        HeadersConfig headers = new HeadersConfig(applicationName, "Get all not deleted Activities");
        return ResponseEntity
                .created(new URI("/api/activity" + "getAllNotDeletedActivities"))
                .headers(headers.getHeaders())
                .body(activityResponse);
    }

    @GetMapping("getAllNotDeletedActivitiesForEmployee")
    @PreAuthorize(COMPANY_PREAUTHORIZE)
    public ResponseEntity<List<ActivityResponse>> getAllNotDeletedActivitiesForEmployee() throws URISyntaxException {
        List<ActivityResponse> activityResponse = activityService.getAllNotDeletedActivitiesForEmployee();
        HeadersConfig headers = new HeadersConfig(applicationName, "Get all not deleted Activities");
        return ResponseEntity
                .created(new URI("/api/activity" + "getAllNotDeletedActivitiesForEmployee"))
                .headers(headers.getHeaders())
                .body(activityResponse);
    }

    @GetMapping("getActivityManager/{activityId}")
    @PreAuthorize(COMPANY_PREAUTHORIZE)
    public ResponseEntity<EmployeeResponse> getActivityManager(@PathVariable Integer activityId) throws URISyntaxException {
        EmployeeResponse employeeResponse = activityService.getActivityManager(activityId);
        HeadersConfig headers = new HeadersConfig(applicationName, "Get activity manager");
        return ResponseEntity
                .created(new URI("/api/activity" + "getActivityManager"))
                .headers(headers.getHeaders())
                .body(employeeResponse);
    }

    @PutMapping("editActivity")
    @PreAuthorize(COMPANY_PREAUTHORIZE)
    public ResponseEntity<?> editActivity(@RequestBody EditActivityRequest editActivityRequest) {
        activityService.editActivity(editActivityRequest);
        return new ResponseEntity<>("{\"Success\":\"Request has been updated!\"}", HttpStatus.OK);
    }
}
