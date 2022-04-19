package main.java.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceStreamEx01 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);
    }
}
