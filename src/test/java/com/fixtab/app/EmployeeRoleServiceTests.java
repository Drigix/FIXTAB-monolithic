package com.fixtab.app;

import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import com.fixtab.app.services.implementations.EmployeeRoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeRoleServiceTests {

    @Autowired
    EmployeeRoleServiceImpl employeeRoleService;

    @Test
    public void onlyThreeRolesShouldExist(){
        //GIVEN
        List<EmployeeRoleModel> allEmployeeRoles = employeeRoleService.getAllEmployeeRoles();

        //WHEN
        int size = allEmployeeRoles.size();

        //THEN
        Assertions.assertEquals(3,size);
    }

    @Test
    public void rolesAdminManagerEmployeeShouldExist(){
        //GIVEN
        List<EmployeeRoleModel> allEmployeeRoles = employeeRoleService.getAllEmployeeRoles();

        //WHEN
        String adminRole = allEmployeeRoles.get(0).getAuthority();
        String managerRole = allEmployeeRoles.get(1).getAuthority();
        String employeeRole = allEmployeeRoles.get(2).getAuthority();

        //THEN
        Assertions.assertEquals("ROLE_ADMIN",adminRole);
        Assertions.assertEquals("ROLE_MANAGER",managerRole);
        Assertions.assertEquals("ROLE_EMPLOYEE",employeeRole);
    }


}
