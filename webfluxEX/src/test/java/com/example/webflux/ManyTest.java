package com.example.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;

class ManyTest {

    @Test
    public void Test() {
        EmitterProcessor<Long> data = EmitterProcessor.create(1);
        data.subscribe(t -> System.out.println(t));
        FluxSink<Long> sink = data.sink();
        sink.next(10L);
        sink.next(11L);
        sink.next(12L);
        data.subscribe(t -> System.out.println(t));
        sink.next(13L);
        sink.next(14L);
        sink.next(15L);
    }

    @Test
    public void Test2() throws InterruptedException {
        EmitterProcessor<String> emitter = EmitterProcessor.create();
        FluxSink<String> sink = emitter.sink();
        emitter.publishOn(Schedulers.single())
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("HELLO"))
                .delayElements(Duration.of(1000, MILLIS))
                .subscribe(System.out::println);

        sink.next("Hello World!");
        sink.next("Goodbye World");
        sink.next("Again");
        Thread.sleep(3000);
    }

    @Test
    public void Test3() throws InterruptedException {
        Flux<Long> flux = Flux.interval(Duration.ofMillis(1000));
        Sinks.Many<String> many = Sinks.many().multicast().onBackpressureBuffer();
//        many.asFlux().subscribe(m ->{ System.out.println(m);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        flux.map(p -> many.tryEmitNext(String.valueOf(p)));
        many.asFlux().subscribe(m -> System.out.println(m));
        Thread.sleep(1000000000L);
        many.tryEmitNext("1");
        many.tryEmitNext("2");
        many.tryEmitNext("3");
        many.tryEmitNext("4");
        many.tryEmitNext("5");
        many.tryEmitNext("Hello");
        many.tryEmitNext("World");

    }
}