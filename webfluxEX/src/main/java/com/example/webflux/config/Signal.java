package com.example.webflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class Signal {

    @Autowired private Sinks.Many<String> many;

//    @Bean
//    public Function<Flux<String>, Mono<Void>> signalin() { //메세지 소비(sink)
//        return p -> p
//                //.doOnNext(q -> wsSink.tryEmitNext(q))
//                //.doOnNext(System.out::println)
//                .doOnNext(q->{
////                    many.tryEmitNext(q);
//                    log.info("reciv : " + q);
//                })
//                .then()
//                ;
//    }

    @Bean
    public Sinks.Many<String> signaloutSink(){
        return Sinks.many().replay().latest();
    }

    @Bean
    public Supplier<Flux<String>> signalout() { //exchange에 발행(source)
        return () -> {
            return signaloutSink().asFlux().onErrorReturn("");
        };
    }
}
