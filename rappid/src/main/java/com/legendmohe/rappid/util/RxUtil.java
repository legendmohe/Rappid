package com.legendmohe.rappid.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by legendmohe on 16/4/13.
 */
public class RxUtil {
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static PublishSubject<Long> getCountDownSubject(int countDown, Subscriber<Long> subscriber) {
        PublishSubject<Long> publishSubject = PublishSubject.create();
        Observable<Long> mCountDownObservable = Observable.interval(1, TimeUnit.SECONDS)
                .take(countDown)
                .takeUntil(publishSubject)
                .observeOn(AndroidSchedulers.mainThread());
        mCountDownObservable.subscribe(subscriber);
        return publishSubject;
    }

    public static <T> void runBackgroundObservable(Observable.OnSubscribe<T> onSubscribe, Subscriber<T> subscriber) {
        Observable.create(onSubscribe)
                .compose(RxUtil.<T>applySchedulers())
                .subscribe(subscriber);
    }
}
