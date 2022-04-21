package com.example.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class TestFluxMono {

    public static void main(String[] args) {

        //String의 시퀀스 Flux
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);
        seq2.subscribe(i -> System.out.println(i));

        //range 메서드 Flux 생성하고 구독
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(i -> System.out.println(i));

        //just메서드를 이용해서 String type의 Mono Publisher를 생성
        Mono<String> data = Mono.just("foo");
    }
}
