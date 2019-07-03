package cn.luo.android.quick.util;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:        2018/2/8 15:55
 * NOTE:
 */
public class RxJavaUtils {

    public static <T> void doIOTask(ObservableOnSubscribe<T> emitter, Observer<T> observer) {
        Observable.create(emitter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static <T> Observable<T> doIOTask(ObservableOnSubscribe<T> emitter) {
        return Observable.create(emitter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Flowable<T> doIOTask(FlowableOnSubscribe<T> emitter) {
        return Flowable.create(emitter, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
