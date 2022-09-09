package org.madbit.r2dbc.controller;

import org.madbit.r2dbc.model.TestTimeout;
import org.madbit.r2dbc.model.TestTimeout2;
import org.madbit.r2dbc.reactive.repository.ReactiveTestRepository;
import org.madbit.r2dbc.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestServerController {

    private Logger logger = LoggerFactory.getLogger(TestServerController.class);

    @Autowired
    private ReactiveTestRepository reactiveTestRepository;

    @Autowired
    private TestRepository testRepository;

//    @GetMapping(value = "/count", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Mono<Long> count(@RequestParam("id") Long id) {
//        return reactiveTestRepository.countById(id);
//    }
//
//    @GetMapping(value = "/join", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Mono<Long> join(@RequestParam("from") Long from, @RequestParam("to") Long to) {
//        return reactiveTestRepository.findJoin(from, to);
//    }

    @GetMapping(value = "/reactive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TestTimeout> getAll() {
        logger.info("Find all");
        return reactiveTestRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public TestTimeout2 getById(@PathVariable("id") Long id) {
        return testRepository.findById(id).get();
    }
}
