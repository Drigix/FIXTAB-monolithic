package com.fixtab.app.services.interfaces;

import com.fixtab.app.models.db.UserModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.CreateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getAllEmployees();

    String createEmployee(CreateEmployeeRequest request);

    EmployeeModel loadUserByEmail(String email);

    void deleteEmployee(Integer employeeId);

    UserModel getUserModel(String email);
}
