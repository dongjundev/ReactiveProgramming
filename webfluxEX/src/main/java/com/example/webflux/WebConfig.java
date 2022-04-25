package com.example.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebConfig {

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public Flux<Long> flux() {
        return Flux.interval(Duration.ofMillis(1000L)).share();
    }

    @Bean
    public Sinks.Many<String> many(Flux<Long> flux) {
        Sinks.Many<String> many = Sinks.many().replay().latest();
        flux.subscribe(p -> many.tryEmitNext(String.valueOf(p)));
        return many;
    }

    @Bean
    public MyWebSocketHandler myWebSocketHandler(Sinks.Many<String> many){
        return new MyWebSocketHandler(many);
    }

    @Bean
    public HandlerMapping handlerMapping(MyWebSocketHandler myWebSocketHandler) {
        Map<String, MyWebSocketHandler> map = new HashMap<>();
        map.put("/path", myWebSocketHandler);
        int order = -1; // before annotated controllers

        return new SimpleUrlHandlerMapping(map, order);
    }
}

