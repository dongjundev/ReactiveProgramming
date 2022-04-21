package com.example.webflux;

import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class MyWebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        return session.receive()            // (1)
                .doOnNext(message -> {
                    System.out.println("message = " + message.getPayloadAsText());
                })
                .then();
    }
}
