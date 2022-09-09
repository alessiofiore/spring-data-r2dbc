package org.madbit.r2dbc.reactive.repository;

import org.madbit.r2dbc.model.TestTimeout;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;
import org.springframework.data.r2dbc.repository.Query;

public interface ReactiveTestRepository extends R2dbcRepository<TestTimeout, Long> {

    @Query("SELECT COUNT(*) from Test_Timeout A JOIN Test_Timeout_2 B ON A.id=B.id WHERE A.id > :from AND A.id < :to")
    public Mono<Long> findJoin(@Param("from") Long from, @Param("to") Long to);

    @Query("SELECT COUNT(*) from TestTimeout A WHERE A.id = :id")
    public Mono<Long> countById(@Param("id") Long id);
}
