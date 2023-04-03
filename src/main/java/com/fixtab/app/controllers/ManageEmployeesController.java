package com.fixtab.app.controllers;

import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("createEmployee")
    public EmployeeModel createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        EmployeeModel employee = employeeService.createEmployee(createEmployeeRequest);
        return employee;
    }
}