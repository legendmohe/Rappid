package com.legendmohe.rappid.bus;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by legendmohe on 16/4/14.
 */
public class RxBus {

    // TODO - need concurrent wrapper?
    Map<Object, Subscriber<Object>> mEventHandlerMap = new HashMap<>();

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void post(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

    public boolean hasObservers() {
        return _bus.hasObservers();
    }

    public void register(Object host) {
        register(host, new RxEnumEventResolver());
    }

    public void register(Object host, RxEventHandler.SubscriptionResolver resolver) {
        if (mEventHandlerMap.containsKey(host))
            throw new IllegalStateException("host has been registered");

        RxEventHandler handler = new RxEventHandler(host, resolver);
        _bus.subscribe(handler);
        mEventHandlerMap.put(host, handler);
    }

    public void unregister(Object host) {
        if (!mEventHandlerMap.containsKey(host))
            throw new IllegalArgumentException("host has not been registered");
        mEventHandlerMap.get(host).unsubscribe();
        mEventHandlerMap.remove(host);
    }
}
