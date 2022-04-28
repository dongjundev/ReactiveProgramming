package com.example.webflux.controller;

import com.example.webflux.domain.Mymessage;
import com.example.webflux.service.MymessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MymessageController {

    private final MymessageService mymessageService;

    @GetMapping("/api/findAll")
    public Flux<Mymessage> findAll() {

        return mymessageService.findAll();
    }

    @GetMapping("/api/save/{message}")
    public Mono<Mymessage> save(@PathVariable String message) {

        return mymessageService.save(new Mymessage(null, message));
    }
}
