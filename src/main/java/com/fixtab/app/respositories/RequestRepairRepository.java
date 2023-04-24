package com.fixtab.app.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fixtab.app.models.db.activities.ActivityModel;
import com.fixtab.app.models.db.activities.RequestModel;

public interface RequestRepairRepository extends JpaRepository<RequestModel, Integer> {
    
    List<RequestModel> findAllByDeletedFalse();

    RequestModel findOneByActivity(ActivityModel activity);
}
