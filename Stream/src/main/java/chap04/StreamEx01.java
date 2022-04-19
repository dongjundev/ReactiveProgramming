package main.java.chap04;

import java.util.List;
import java.util.stream.Collectors;

import static main.java.chap04.Dish.menu;

public class StreamEx01 {

    public static void main(String[] args) {

        List<String> names = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);
    }
}
