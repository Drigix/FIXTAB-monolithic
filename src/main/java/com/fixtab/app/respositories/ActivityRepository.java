package com.fixtab.app.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.employees.EmployeeModel;

public interface ActivityRepository extends JpaRepository<ActivityModel, Integer> {
    
    List<ActivityModel> findAllByDeletedFalse();

    List<ActivityModel> findAllByDeletedFalseAndEmployee(EmployeeModel employee);
}
