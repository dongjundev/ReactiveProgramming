package main.java.ch05;

import main.java.chap04.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static main.java.chap04.Dish.menu;

public class LimitStreamEx01 {
    public static void main(String[] args) {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(dishes);
    }
}
