package com.fixtab.app.models.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EditEmployeeRequest extends CreateEmployeeRequest {
    private Integer employeeId;
}
