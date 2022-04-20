package ch06.backpressureStrategy.latest;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Latest {
    public static void main(String[] args) {
        Flowable.interval(300L,TimeUnit.MILLISECONDS)
                .doOnNext(data->Logger.log("#invervaldoOnNext()",data))
                .onBackpressureLatest()
                .observeOn(Schedulers.computation(),false,1)
                .subscribe(
                        data->{
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT,data);
                        },
                        error->Logger.log(LogType.ON_ERROR,error)
                );
    }
}
