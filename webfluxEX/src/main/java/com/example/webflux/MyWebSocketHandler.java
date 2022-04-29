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
    private static Sinks.Many<Mymessage> backUpMany = Sinks.many().replay().all();

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        session.receive().subscribe(p->many.tryEmitNext(p.getPayloadAsText()));

//        many.asFlux().subscribe(p -> mymessageService.save(new Mymessage(null, p)).subscribe());

        Flux<Flux<String>> window = many.asFlux().window(10);
        Flux<Flux<Mymessage>> map = window.map(p -> p.map(q -> new Mymessage(null, q)));

        try {
            map.subscribe(p -> mymessageService.saveAll(p).subscribe());
        } catch (Exception e) {
            map.subscribe(p -> p.subscribe(q -> backUpMany.tryEmitNext(q)));
            log.info("에러 캐치!!");
        }

        backUpMany.asFlux().subscribe(p -> mymessageService.save(p).subscribe());

        Mono<Void> output = session.send(many.asFlux().map(p -> session.textMessage(p)));
        Mono<Void> queue = many.asFlux().map(q -> signaloutSink.tryEmitNext(q)).then();

        return Mono.zip(output, queue).then();
    }
}
