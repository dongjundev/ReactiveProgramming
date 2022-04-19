package main.java.chap03;

import java.util.function.Function;

public class Function01 {

    public static void main(String[] args) {

        Function<String, Integer> f = (s) -> Integer.parseInt(s, 16); //s를 16진 정수로 변환
        Function<Integer, String> g = (i) -> Integer.toBinaryString(i); //2진 문자열로 변환
    }
}
