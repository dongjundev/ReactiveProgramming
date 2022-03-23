package main.java.chap03;

import java.util.function.Predicate;

public class Predicate01 {

    public static void main(String[] args) {

        //Predicate는 조건식을 표현하는데 사용
        Predicate<String> isEmptyStr = s -> s.length()==0;
        String s = "";

        if(isEmptyStr.test(s)) //if(s.length()==0)
            System.out.println("Empty String!");
    }
}
