package com.fixtab.app.services.interfaces;

import java.util.List;

import com.fixtab.app.models.db.activities.RequestModel;
import com.fixtab.app.models.requests.RequestRepairRequest;
import com.fixtab.app.models.responses.RequestRepairResponse;

public interface RequestRepairService {
    
    List<RequestModel> getAllRequestRepairs();

    List<RequestRepairResponse> getAllNotDeletedRequestRepairs();

    void createRequestRepair(RequestRepairRequest requestRepairRequest);

}
