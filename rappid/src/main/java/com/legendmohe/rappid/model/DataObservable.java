package com.legendmohe.rappid.model;

/**
 * Created by legendmohe on 16/4/27.
 */
public interface DataObservable<T> {

    void register(DataObserver<T> observer);

    void unregister(DataObserver<T> observer);

    void notifyDataUpdated(T item);

    void notifyDataAdded(T item);

    void notifyDataRemove(T item);
}
