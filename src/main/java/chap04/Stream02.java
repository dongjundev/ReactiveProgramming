package main.java.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream02 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> intStream = list.stream(); //Collection
        Stream<String> strStream = Stream.of(new String[]{"a", "b", "c"}); //배열
        Stream<Double> ranStream = Stream.generate(Math::random); //메소드 레퍼런스
        IntStream intStream1 = new Random().ints(5); //난수 스트림
    }
}
