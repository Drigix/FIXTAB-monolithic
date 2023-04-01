package com.fixtab.app.respositories;

import com.fixtab.app.models.db.employees.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {
    Optional<EmployeeModel> findByEmail(String email);
}
