package com.fixtab.app.services.interfaces;

import com.fixtab.app.models.db.employees.EmployeeRoleModel;

import java.util.List;
import java.util.Optional;

public interface EmployeeRoleService {
    List<EmployeeRoleModel> getAllEmployeeRoles();
    Optional<EmployeeRoleModel> getEmployeeRole(int roleId);
}
