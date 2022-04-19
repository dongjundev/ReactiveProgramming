package main.java.ch05;

import main.java.chap04.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static main.java.chap04.Dish.menu;

public class FilterStreamEx01 {
    public static void main(String[] args) {

        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println(vegetarianMenu);
    }
}
