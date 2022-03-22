package main.java.chap02;

//함수형 인터페이스
@FunctionalInterface
interface MyFuction1 {
    int calc(int x);
}

@FunctionalInterface
interface MyFunction2 {
    int calc(int x, int y);
}

public class LambdaEx02 {
    public LambdaEx02() {
        //매개변수가 1개인 람다식
        MyFuction1 result1 = (x) -> {return x + x;};
        MyFuction1 result2 = x -> x + x;
        System.out.println("result1 = " + result1.calc(5));
        System.out.println("result2 = " + result2.calc(5));

        //매개변수가 2개인 람다식
        MyFunction2 add = (x, y) -> { return x + y}; //람다식
        MyFunction2 minus = (x, y) -> x - y; //람다식 {}, return 생략
        System.out.println("add = " + add.calc(3, 5));
        System.out.println("minus = " + minus.calc(3, 5));

        MyFunction2 f = new MyFunction2() {
            @Override
            public int calc(int x, int y) {
                return x + y;
            }
        };

        int value = f.calc(5, 3);
        System.out.println("value = " + value);

        MyFunction2 f2 = (a, b) -> a > b ? a : b;
        value = f2.calc(3, 2);
        System.out.println("value = " + value);
    }

    public static void main(String[] args) {
        new LambdaEx02();
    }
}
