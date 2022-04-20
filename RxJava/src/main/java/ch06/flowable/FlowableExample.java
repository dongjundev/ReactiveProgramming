package ch06.flowable;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableExample {
    public static void main(String[] args) {
        Flowable<String> flowable =
                Flowable.create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(FlowableEmitter<String> emitter)
                            throws Exception {

                        String[] datas = {"Hello,World!", "안녕,RxJava!"};

                        for (String data : datas) {
                            if (emitter.isCancelled()) {
                                return;
                            }

                            emitter.onNext(data);
                        }

                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER);


        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {

                    private Subscription subscription;

                    public void onSubscribe (Subscription subscription){
                        this.subscription = subscription;
                        this.subscription.request(1L); //데이터개수요청
                    }

                    public void onNext (String data) {
                        System.out.println(data);
                        this.subscription.request(1L); //다음에받을데이터개수요청
                    }

                    public void onComplete () {
                        System.out.println("완료");
                    }

                    public void onError (Throwable error){
                        error.printStackTrace();
                    }
                });
    }
}
