package com.fixtab.app.respositories;

import com.fixtab.app.models.db.customers.PasswordModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordRepository extends JpaRepository<PasswordModel, Integer> {

    public Optional<PasswordModel> findByEmployeeId(int id);

}
