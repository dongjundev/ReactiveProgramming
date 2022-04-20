package ch06.backpressureStrategy.buffer;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class DropLatest {

    public static void main(String[] args) {

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log("#inverval doOnNext()", data))
                .onBackpressureBuffer(
                        2,
                        () -> Logger.log("overflow!"),
                        BackpressureOverflowStrategy.DROP_LATEST)
                .doOnNext(data -> Logger.log("#onBackpressureBuffer doOnNext()", data))
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_ERROR, error);
                        }
                );
        TimeUtil.sleep(2800L);
    }
}
