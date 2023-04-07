package com.fixtab.app.respositories;

import com.fixtab.app.models.db.customers.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientModel, Integer> {

    List<ClientModel> findAllByDeletedFalse();
}
