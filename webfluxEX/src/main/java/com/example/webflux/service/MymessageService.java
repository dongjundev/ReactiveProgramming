package com.example.webflux.service;

import com.example.webflux.domain.Mymessage;
import com.example.webflux.repository.MymessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MymessageService {

    private final MymessageRepository myMessageRepository;

    public Mono<Mymessage> save(Mymessage mymessage) {

        return myMessageRepository.save(mymessage);
    }

    public Flux<Mymessage> findAll() {

        return myMessageRepository.findAll();
    }

    public Flux<Mymessage> saveAll(Flux<Mymessage> flux) {

        return myMessageRepository.saveAll(flux);
    }

}
