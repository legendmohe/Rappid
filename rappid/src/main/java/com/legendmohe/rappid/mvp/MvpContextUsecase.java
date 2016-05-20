package com.legendmohe.rappid.mvp;

import android.content.Context;

import com.legendmohe.rappid.bus.BusProvider;


/**
 * Created by legendmohe on 16/4/12.
 */
public abstract class MvpContextUsecase<T> implements MvpBaseUsecase<T> {

    Context mContext;

    public MvpContextUsecase(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public void postEvent(Enum flag, Object data) {
        BusProvider.getUIBusInstance().post(new MvpBaseEvent(flag, data));
    }

    public void postEvent(Enum flag) {
        postEvent(flag, null);
    }

    @Override
    public void stop() {
    }

    @Override
    public void cancel() {
    }
}
