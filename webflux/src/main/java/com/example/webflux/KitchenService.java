package com.example.webflux;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class KitchenService {

    Flux<Dish> getDishes() {
        //무작위로 제공되는 요리를 Flux의 핸들로, Flux에 포함될 요소를 동적으로 발행해준다.
        return Flux.<Dish> generate(sink -> sink.next(randomDish()))
                .delayElements(Duration.ofMillis(250));
    }

    private Dish randomDish() {return menu.get(picker.nextInt(menu.size()));}

    private List<Dish> menu = Arrays.asList(
            new Dish("Sesame chicken"),
            new Dish("Lo mein noodles, plain"),
            new Dish("Sweet & sour beef"));

    private Random picker = new Random();
}
