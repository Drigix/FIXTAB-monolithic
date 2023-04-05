package com.fixtab.app.respositories;

import com.fixtab.app.models.db.employees.EmployeeRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRoleModel, Integer> {
}
