package com.legendmohe.rappid.mvp;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by legendmohe on 16/6/10.
 */
public abstract class ObservableMvpBaseUsecase<T, P> {
    public abstract T execute(P... params);

    public Observable<T> create(final P... params) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    T result = execute(params);
                    subscriber.onNext(result);
                    subscriber.onCompleted();
                } catch (Throwable throwable) {
                    subscriber.onError(throwable);
                }
            }
        });
    }
}
