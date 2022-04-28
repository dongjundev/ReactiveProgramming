package com.example.webflux;

import com.example.webflux.domain.Mymessage;
import com.example.webflux.service.MymessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RequiredArgsConstructor
@Component
@Slf4j
public class MyWebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

    private final Sinks.Many<String> many;
    private final Sinks.Many<String> signaloutSink;
    private final MymessageService mymessageService;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        session.receive().subscribe(p->many.tryEmitNext(p.getPayloadAsText()));

        many.asFlux().subscribe(p -> mymessageService.save(new Mymessage(null, p)).subscribe());

        Mono<Void> output = session.send(many.asFlux().map(p -> session.textMessage(p)));
        Mono<Void> queue = many.asFlux().map(q -> signaloutSink.tryEmitNext(q)).then();

        return Mono.zip(output, queue).then();
    }
}
