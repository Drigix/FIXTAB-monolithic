package com.fixtab.app.controllers;

import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import com.fixtab.app.services.interfaces.EmployeeRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employeeRoles")
public class EmployeeRoleController {

    private final EmployeeRoleService employeeRoleService;

    @GetMapping("getAllRoles")
    public List<EmployeeRoleModel> getAllEmployeeRoles() {
        return employeeRoleService.getAllEmployeeRoles();
    }
}
