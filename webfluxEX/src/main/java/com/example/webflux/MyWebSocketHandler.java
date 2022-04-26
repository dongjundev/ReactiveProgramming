package com.example.webflux;

import com.example.webflux.domain.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RequiredArgsConstructor
@Component
public class MyWebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

//    private static Flux<Long> flux = Flux.interval(Duration.ofMillis(1000L)).share();
//    private static Sinks.Many<String> many = Sinks.many().replay().latest();
//    private static boolean flag = true;

//    @Autowired
//    Sinks.Many<String> many;

    private final Sinks.Many<String> many;
    //private final StreamBridge streamBridge;

    private final Sinks.Many<String> signaloutSink;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

//        ApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);
//        Sinks.Many<String> many = ac.getBean("many", Sinks.Many.class);

//        인바운드
//        session.receive()
//                .doOnNext(message -> {
//                    System.out.println("message = " + message.getPayloadAsText());
//                })
//                .then();

//        if (flag) {
//            flux.subscribe(p -> many.tryEmitNext(String.valueOf(p)));
//            flag = false;
//        }

        session.receive().subscribe(p->many.tryEmitNext(p.getPayloadAsText()));
        Mono<Void> output = session.send(many.asFlux().map(p -> session.textMessage(p)));
        Mono<Void> queue = many.asFlux().map(q -> signaloutSink.tryEmitNext(q))
                .then();

        return Mono.zip(output, queue).then();

//        return session.send(many.asFlux().map(p -> session.textMessage(p)));

//        Mono<Void> input = session.receive()
//                .doOnNext(m -> System.out.println("client = " + m.getPayloadAsText()))
//                .map(m -> many.tryEmitNext(m.getPayloadAsText()))
//                .then();

        //Mono<Void> output = session.send(many.asFlux().map(p -> session.textMessage(p)));

        //return Mono.zip(input, output).then();

    }
}
