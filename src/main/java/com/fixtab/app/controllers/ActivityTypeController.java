package com.fixtab.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fixtab.app.models.db.activities.ActivityTypeModel;
import com.fixtab.app.services.interfaces.ActivityTypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.fixtab.app.security.AuthoritiesConstants.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/activity-type")
public class ActivityTypeController {
    
    private final ActivityTypeService activityTypeService;

    private final String applicationName = "FIXTAB acitivityTypeManager: ";

    @GetMapping("getAllActivityTypes")
    @PreAuthorize(MANAGEMENT_PREAUTHORIZE)
    public List<ActivityTypeModel> getAllActivityTypes() throws URISyntaxException {
        log.debug(applicationName + "Request to get all activity types");
        return activityTypeService.getAll();
    }
}
