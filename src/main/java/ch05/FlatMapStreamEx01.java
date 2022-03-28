package main.java.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapStreamEx01 {
    public static void main(String[] args) {

        List<String> words = Arrays.asList("Hello", "World");

        List<String> uniqueChars = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueChars);
    }
}
