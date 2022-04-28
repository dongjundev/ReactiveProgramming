package com.example.webflux.repository;

import com.example.webflux.domain.Mymessage;
import com.example.webflux.service.MymessageService;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@Slf4j
class MymessageRepositoryTest {

    @Autowired
    private MymessageRepository myMessageRepository;

    @Autowired
    private MymessageService mymessageService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void select() {
        myMessageRepository.findAll()
                .as(publisher -> StepVerifier.create(publisher))
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void insert() {
        Mymessage mymessage1 = new Mymessage(null,"testMessage10");
        mymessageService.save(mymessage1)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
//        Mono<Mymessage> save = mymessageService.save(mymessage1)

    }
}