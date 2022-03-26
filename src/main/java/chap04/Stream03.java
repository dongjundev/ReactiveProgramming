package main.java.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream03 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 5, 4, 2);
        List<Integer> sortedList = list.stream().sorted() //list를 정렬해서
                .collect(Collectors.toList()); //새로운 list에 저장

        //스트림은 데이터 소스로부터 데이터를 읽기만할 뿐 변경하지 않음.
        System.out.println(list);
        System.out.println(sortedList);

        String[] words = {"aed", "bsed", "c"};

        //스트림은 Iterator처럼 일회용이다.(필요하면 다시 생성)
        Arrays.stream(words).forEach(System.out::println);
        //int numOfStr = Arrays.stream(words).count(); //에러. 스트림이 이미 닫힘.


    }
}
