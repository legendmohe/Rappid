package com.legendmohe.rappid.mvp;

/**
 * Created by legendmohe on 16/3/29.
 */
public interface MvpBaseUsecase<T> {
    MvpBaseUsecase execute(T... params);

    void stop();

    void cancel();
}
