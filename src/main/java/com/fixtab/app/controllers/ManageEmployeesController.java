package com.fixtab.app.controllers;

import com.fixtab.app.models.db.employees.EmployeeModel;
import com.fixtab.app.models.requests.CreateEmployeeRequest;
import com.fixtab.app.models.requests.EditEmployeeRequest;
import com.fixtab.app.models.responses.EmployeeResponse;
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

    @GetMapping("getAllNotDeletedEmployees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeModel> getAllNotDeletedEmployees() {
        return employeeService.getAllNotDeletedEmployees();
    }

    @PostMapping("createEmployee")
    public String createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        return employeeService.createEmployee(createEmployeeRequest);
    }

    @PutMapping("editEmployee")
    public EmployeeResponse createEmployee(@RequestBody EditEmployeeRequest editEmployeeRequest) {
        return employeeService.editEmployee(editEmployeeRequest);
    }

    @DeleteMapping("deleteEmployee/{employeeId}")
    public void createEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
