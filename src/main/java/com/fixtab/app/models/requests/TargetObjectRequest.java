package com.fixtab.app.models.requests;

import com.fixtab.app.models.db.customers.ObjectTypeModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TargetObjectRequest {
    private String name;
    private Integer clientId;
    private ObjectTypeModel objectType;
}
