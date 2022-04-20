package ch06.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ObservableExample {

    public static void main(String[] args) {

        Observable<String> observable =
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter)
                            throws Exception {


                        String[] datas = {"Hello,World!", "안녕,RxJava!"};
                        for (String data : datas) {

                            if (emitter.isDisposed()) {
                                return;
                            }

                            emitter.onNext(data);
                        }

                        emitter.onComplete();
                    }
                });


        observable
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<String>(){
            public void onSubscribe(Disposable disposable){
//                ...
            }

            public void onNext(String item){
                System.out.println(item);
            }

            public void onComplete(){
                System.out.println("완료");
            }

            public void onError(Throwable error){
                error.printStackTrace();
            }
        });
    }
}
