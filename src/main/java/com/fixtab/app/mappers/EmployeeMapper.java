package com.fixtab.app.mappers;

import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.models.requests.EditEmployeeRequest;
import com.fixtab.app.models.responses.EmployeeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper {
    EmployeeResponse toResponse(EmployeeModel employeeModel);

    EmployeeModel toEntity(CreateEmployeeRequest employeeRequest);

    EmployeeModel toEntity(EditEmployeeRequest employeeRequest);

    default EmployeeModel fromId (Integer id) {
        if (id == null) {
            return null;
        }
        EmployeeModel user = EmployeeModel.builder().build();
        EmployeeModel.builder().employeeId(id);
        return user;
    }
}
