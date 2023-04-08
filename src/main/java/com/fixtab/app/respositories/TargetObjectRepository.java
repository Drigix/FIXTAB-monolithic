package com.fixtab.app.respositories;

import com.fixtab.app.models.db.customers.TargetObjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetObjectRepository extends JpaRepository<TargetObjectModel, Integer> {
    List<TargetObjectModel> findAllByDeletedFalse();
}
