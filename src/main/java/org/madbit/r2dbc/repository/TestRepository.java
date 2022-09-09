package org.madbit.r2dbc.repository;

import org.madbit.r2dbc.model.TestTimeout;
import org.madbit.r2dbc.model.TestTimeout2;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<TestTimeout2, Long> {
}
