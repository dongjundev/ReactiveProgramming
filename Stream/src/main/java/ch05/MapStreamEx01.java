package main.java.ch05;

import main.java.chap04.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static main.java.chap04.Dish.menu;

public class MapStreamEx01 {

    public static void main(String[] args) {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames);

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNameLengths);
    }
}
