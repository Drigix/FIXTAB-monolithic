package com.fixtab.app.respositories;

import com.fixtab.app.models.db.customers.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, Integer> { }
