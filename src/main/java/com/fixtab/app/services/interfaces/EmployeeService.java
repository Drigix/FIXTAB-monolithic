package com.fixtab.app.services.interfaces;

import com.fixtab.app.models.db.UserModel;
import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.ChangePasswordRequest;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.models.requests.EditEmployeeRequest;
import com.fixtab.app.models.responses.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getAllEmployees();

    List<EmployeeResponse> getAllNotDeletedEmployees();

    EmployeeResponse getCurrentEmployee();

    UserModel getUserModel(String email);

    String createEmployee(CreateEmployeeRequest request);

    void changePassword(ChangePasswordRequest changePasswordRequest);

    EmployeeResponse editEmployee(EditEmployeeRequest request);

    EmployeeModel loadUserByEmail(String email);

    void deleteEmployee(Integer employeeId);

}
