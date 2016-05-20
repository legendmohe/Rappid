package com.legendmohe.rappid;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.legendmohe.rappid.bus.BusProvider;
import com.legendmohe.rappid.bus.RxBus;

import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static final String TAG = "ApplicationTest";

    public ApplicationTest() {
        super(Application.class);

        final RxBus rxBus = BusProvider.getRxBusInstance();

        rxBus.toObserverable().subscribeOn(Schedulers.newThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.d(TAG, "call " + o + " on " + Thread.currentThread().getId());
            }
        });

        Log.d(TAG, "start " + Thread.currentThread().getId());
        new Thread() {
            @Override
            public void run() {
                Log.d(TAG, "post " + Thread.currentThread().getId());
                rxBus.post("hello");
            }
        }.start();
        rxBus.post("world");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}