package ch06;

import io.reactivex.rxjava3.core.Flowable;

public class OperatorExample {

    public static void main(String[] args) {

        //Flowable = Publisher?
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(data -> data % 2 == 0)
                .map(data -> data * 2);

        flowable.subscribe(data -> System.out.println("구독 받은 데이터: " + data));
    }
}
