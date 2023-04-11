package com.fixtab.app.services.implementations;

import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import com.fixtab.app.respositories.EmployeeRoleRepository;
import com.fixtab.app.services.interfaces.EmployeeRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

    private final EmployeeRoleRepository employeeRoleRepository;

    @Override
    public List<EmployeeRoleModel> getAllEmployeeRoles() {
        return employeeRoleRepository.findAll();
    }
    @Override
    public Optional<EmployeeRoleModel> getEmployeeRole(int roleId) {
        return employeeRoleRepository.findByRoleId(roleId);
    }

}


