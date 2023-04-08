package com.fixtab.app.models.responses;

import com.fixtab.app.models.db.customers.ClientModel;
import com.fixtab.app.models.db.customers.ObjectTypeModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TargetObjectResponse {
    private Integer targetId;
    private String name;
    private ClientModel client;
    private ObjectTypeModel objectType;
}
