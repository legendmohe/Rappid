package com.legendmohe.rappid;

import com.legendmohe.rappid.bus.BusProvider;
import com.legendmohe.rappid.bus.RxBus;
import com.legendmohe.rappid.bus.RxEvent;
import com.legendmohe.rappid.bus.RxEventHandler;
import com.legendmohe.rappid.bus.RxSubscribe;
import com.legendmohe.rappid.bus.RxSubscribeEnum;

import org.junit.Test;

import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    void print(String s) {
        System.out.println(s);
    }

    @Test
    public void testRxBus() throws Exception {
        final RxBus rxBus = BusProvider.getRxBusInstance();

        rxBus.toObserverable().subscribeOn(Schedulers.newThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                print("call " + Thread.currentThread().getId());
            }
        });

        print("start " + Thread.currentThread().getId());
        new Thread() {
            @Override
            public void run() {
                print("post " + Thread.currentThread().getId());
                rxBus.post(new TestEvent());
            }
        }.start();

        Thread.sleep(10000);
    }

    //    @Test
    public void testRxSubscribeHandler() throws Exception {
        RxBus rxBus = BusProvider.getRxBusInstance();
        RxEventHandler handler = new RxEventHandler(this);
        rxBus.toObserverable()
                .subscribe(handler);

        print("post thread:" + Thread.currentThread().getId());
        rxBus.post(new TestEvent());
    }

    //    @Test
    public void testRxSubscribeEnumHandler() throws Exception {
        SubClass subClass = new SubClass();
        BusProvider.getRxBusInstance().register(subClass);

        BusProvider.getRxBusInstance().post(RxEvent.createEnumEvent(TestEnumEvent2.BEGIN, 1));
        BusProvider.getRxBusInstance().post(RxEvent.createEnumEvent(TestEnumEvent.START, 1));
        BusProvider.getRxBusInstance().post(RxEvent.createEnumEvent(TestEnumEvent2.FINISH, 2));
        BusProvider.getRxBusInstance().post(RxEvent.createEnumEvent(TestEnumEvent.STOP, 2));
    }

    //    @Test
    public void testBenchmark() throws Exception {
        BusProvider.getRxBusInstance().register(this);
        BusProvider.getRestBusInstance().register(this);

        int times = 10000000;

        long beginTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            BusProvider.getRxBusInstance().post(RxEvent.createEnumEvent(TestEnumEvent2.BEGIN, 1));
        }
        print("RxBus benchmark: " + (System.nanoTime() - beginTime));

        beginTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            BusProvider.getRestBusInstance().post(new TestEvent());
        }
        print("Otto  benchmark: " + (System.nanoTime() - beginTime));
    }

    //    @RxSubscribe
    public void onTestEvent(TestEvent event) {
        print("onTestEvent recv: " + event.flag + " onThread:" + Thread.currentThread().getId());
    }

    //    @RxSubscribe
    public void onTestEvent2(TestEvent event) {
        print("onTestEvent2 recv: " + event.flag + " onThread:" + Thread.currentThread());
    }

    //    @Subscribe
    public void onOttoEvent(TestEvent event) {
//        print("onOttoEvent:" + event);
    }

    @RxSubscribeEnum
    public void onTestEnumEvent(TestEnumEvent event, Object data) {
        switch (event) {
            case START:
                print("recv: START " + data);
                break;
            case STOP:
                print("recv: STOP " + data);
                break;
        }
    }

    //    @RxSubscribeEnum
    public void onTestEnumEvent2(TestEnumEvent2 event, Object data) {
        switch (event) {
            case BEGIN:
                print("recv: BEGIN " + data);
                break;
            case FINISH:
                print("recv: FINISH " + data);
                break;
        }
    }

    static int mLevel = 0;

    class TestEvent {

        public TestEvent() {
            this.flag = mLevel++;
        }

        int flag;
        Object data;
    }

    public class BaseClass extends android.app.Activity {
        @RxSubscribeEnum
        public void onTestEnumEventBaseClass(TestEnumEvent2 event, Object data) {
            switch (event) {
                case BEGIN:
                    print("onTestEnumEventBaseClass: BEGIN " + data);
                    break;
                case FINISH:
                    print("onTestEnumEventBaseClass: FINISH " + data);
                    break;
            }
        }
    }

    public class SubClass extends BaseClass {

        @RxSubscribe
        public void onTestEvent(TestEvent event) {
            print("onTestEvent recv: " + event.flag);
        }

        @RxSubscribeEnum
        public void onTestEnumEventSubClass(TestEnumEvent2 event, Object data) {
            switch (event) {
                case BEGIN:
                    print("onTestEnumEventSubClass: BEGIN " + data);
                    break;
                case FINISH:
                    print("onTestEnumEventSubClass: FINISH " + data);
                    break;
            }
        }
    }

    public enum TestEnumEvent {
        START,
        STOP
    }

    public enum TestEnumEvent2 {
        BEGIN,
        FINISH
    }
}