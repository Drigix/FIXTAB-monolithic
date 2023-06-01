package com.fixtab.app.respositories;

import com.fixtab.app.models.db.activities.StatusDictionaryModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusDictionaryRepository extends JpaRepository<StatusDictionaryModel, Integer> {
    StatusDictionaryModel findOneByName(String name);
}
