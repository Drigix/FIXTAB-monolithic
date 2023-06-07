package com.fixtab.app.respositories;

import com.fixtab.app.models.db.activities.ActivityTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityTypeModel, Integer> { }
