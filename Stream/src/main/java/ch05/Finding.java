package main.java.ch05;

import main.java.chap04.Dish;

import java.util.Optional;

import static main.java.chap04.Dish.menu;

public class Finding {

    public static void main(String[] args) {
        if (isVegetarianFriendlyMenu()) {
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthMenu());
        System.out.println(isHealthMenu2());

        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthMenu() {
        return menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
    }

    private static boolean isHealthMenu2() {
        return menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish() {
        return menu.stream().filter(Dish::isVegetarian)
                .findAny();
    }
}
