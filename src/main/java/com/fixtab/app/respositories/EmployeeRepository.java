package com.fixtab.app.respositories;

import com.fixtab.app.models.db.employees.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {

    List<EmployeeModel> findAllByDeletedFalse();
    Optional<EmployeeModel> findByEmail(String email);
}
