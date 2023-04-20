package com.fixtab.app.models.requests;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RequestRepairRequest {

    private String description;

    private Integer targetObjectId;

    private Integer managerId;

    private List<ActivityRequest> activities;
}