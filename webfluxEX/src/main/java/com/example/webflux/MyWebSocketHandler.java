package com.example.webflux;

import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class MyWebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

    private static Flux<Long> flux = Flux.interval(Duration.ofMillis(1000L)).share();
    private static Sinks.Many<String> many = Sinks.many().replay().latest();
    private static boolean flag = true;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

//        인바운드
//        session.receive()
//                .doOnNext(message -> {
//                    System.out.println("message = " + message.getPayloadAsText());
//                })
//                .then();

        if (flag) {
            flux.subscribe(p -> many.tryEmitNext(String.valueOf(p)));
            flag = false;
        }

        session.receive().subscribe(p->many.tryEmitNext(p.getPayloadAsText()));
        return session.send(many.asFlux().map(p -> session.textMessage(p)));

//        Mono<Void> input = session.receive()
//                .doOnNext(m -> System.out.println("client = " + m.getPayloadAsText()))
//                .map(m -> many.tryEmitNext(m.getPayloadAsText()))
//                .then();

        //Mono<Void> output = session.send(many.asFlux().map(p -> session.textMessage(p)));

        //return Mono.zip(input, output).then();

    }
}
