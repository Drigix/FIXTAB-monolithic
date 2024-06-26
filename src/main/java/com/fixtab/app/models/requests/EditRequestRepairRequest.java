package com.fixtab.app.models.requests;

import java.util.Date;

import com.fixtab.app.models.db.activities.StatusDictionaryModel;
import com.fixtab.app.models.db.customers.TargetObjectModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EditRequestRepairRequest {

    private Integer requestId;

    private String description;

    private String result;

    private boolean requestCancelled;
    
    private Date openDate;

    private TargetObjectModel targetObject;

    private StatusDictionaryModel status;
}
