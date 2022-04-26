package com.example.reactiveMqCloudEX.controller;

import com.example.reactiveMqCloudEX.domain.MyMessage;
import com.example.reactiveMqCloudEX.domain.ProducerChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final StreamBridge streamBridge;
    private final Sinks.Many<String> many;

    @GetMapping(value = "/direct/{message}")
    public Mono<Void> directMessage(@PathVariable String message) {

//        System.out.println(message);

        return many.asFlux().map(q -> streamBridge.send(ProducerChannel.DIRECT, MyMessage.builder().message(q).build()))
                .then();

//        return Mono.just(message)
//                .doOnNext(s -> streamBridge.send(ProducerChannel.DIRECT, MyMessage.builder().message(message).build()))
//                .then();
    }

    @GetMapping(value = "/broadcast/{message}")
    public Mono<Void> broadcastMessage(@PathVariable String message) {

        System.out.println(message);

        return Mono.just(message)
                .doOnNext(s -> streamBridge.send(ProducerChannel.BROADCAST, MyMessage.builder().message(message).build()))
                .then();
    }
}
