package com.fixtab.app.controllers;

import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.ChangePasswordRequest;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.models.requests.EditEmployeeRequest;
import com.fixtab.app.models.responses.CreateEmployeeResponse;
import com.fixtab.app.models.responses.EmployeeResponse;
import com.fixtab.app.services.interfaces.EmployeeService;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* NEED TO AUTHORIZE THE USER !!!!!!!!!!!!!!!!!
* */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class ManageEmployeesController {
    private final EmployeeService employeeService;

    @GetMapping("getAllEmployees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeModel> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("getAllNotDeletedEmployees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getAllNotDeletedEmployees() {
        return employeeService.getAllNotDeletedEmployees();
    }

    @PostMapping("createEmployee")
    @ResponseBody
    public ResponseEntity<CreateEmployeeResponse> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        String result = employeeService.createEmployee(createEmployeeRequest);
        CreateEmployeeResponse employeeResponse = new CreateEmployeeResponse();
        employeeResponse.setPassword(result);
        return ResponseEntity.ok().body(employeeResponse);
    }

    @PutMapping("changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        employeeService.changePassword(changePasswordRequest);
        return new ResponseEntity<>("{\"Success\":\"Password has been change!\"}",HttpStatus.OK);
    }

    @PutMapping("editEmployee")
    public EmployeeResponse editEmployee(@RequestBody EditEmployeeRequest editEmployeeRequest) {
        return employeeService.editEmployee(editEmployeeRequest);
    }

    @DeleteMapping("deleteEmployee/{employeeId}")
    public void createEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
