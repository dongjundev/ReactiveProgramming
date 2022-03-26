package main.java.chap04;

import java.util.stream.Stream;

public class Stream04 {
    public static void main(String[] args) {
        Stream<String> strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        int sum = strStream.parallel()
                .mapToInt(s -> s.length()).sum();
    }
}
