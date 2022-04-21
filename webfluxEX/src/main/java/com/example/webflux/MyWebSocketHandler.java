package com.example.webflux;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MyWebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {

//        session.receive()
//                .doOnNext(message -> {
//                    System.out.println("message = " + message.getPayloadAsText());
//                })
//                .then();

        Flux<Long> flux = Flux.interval(Duration.ofMillis(1000L)).share();
        ConnectableFlux<Long> hotFlux = flux.publish();

        Mono<Void> output = session.send(flux.map(p -> session.textMessage(String.valueOf(p))));
//        Mono<Void> output = session.send(hotFlux.map(p -> session.textMessage(String.valueOf(p))));
//        hotFlux.connect();

        return output;
    }
}
