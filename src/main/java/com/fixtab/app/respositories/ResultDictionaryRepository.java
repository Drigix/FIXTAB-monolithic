package com.fixtab.app.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fixtab.app.models.db.activities.ResultDictionaryModel;

public interface ResultDictionaryRepository extends JpaRepository<ResultDictionaryModel, Integer> {
    
    ResultDictionaryModel findOneByName(String name);
}
