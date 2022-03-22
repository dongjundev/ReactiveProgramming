package main.java.chap02;

@FunctionalInterface
interface MyFunction4 {
    int calc(int x, int y);
}

public class LambdaEx04 {

    public static void main(String[] args) {
        printSum(10, 5, ((x, y) -> x + y)); //람다식으로 구현
    }

    static void printSum(int x, int y, MyFunction4 f) {
        System.out.println(f.calc(x, y));
    }
}
