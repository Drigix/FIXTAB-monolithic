package com.fixtab.app.services.interfaces;

import com.fixtab.app.models.db.customers.TargetObjectModel;
import com.fixtab.app.models.requests.EditTargetObjectRequest;
import com.fixtab.app.models.requests.TargetObjectRequest;
import com.fixtab.app.models.responses.TargetObjectResponse;

import java.util.List;

public interface TargetObjectService {

    List<TargetObjectModel> getAllTargetObjects();

    List<TargetObjectResponse> getAllNotDeletedTargetObjects();

    TargetObjectResponse createTargetObject(TargetObjectRequest targetObjectRequest);

    void editTargetObject(EditTargetObjectRequest targetObjectRequest);

    void deleteTargetObject(Integer targetId);
}
