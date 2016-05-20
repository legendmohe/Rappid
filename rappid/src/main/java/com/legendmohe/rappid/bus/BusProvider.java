package com.legendmohe.rappid.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by legendmohe on 16/3/29.
 */
public class BusProvider {

    private static final Bus REST_BUS = new Bus(ThreadEnforcer.ANY);
    private static final Bus UI_BUS = new Bus();
    private static final RxBus RX_BUS = new RxBus();

    private BusProvider() {
    }

    public static Bus getRestBusInstance() {

        return REST_BUS;
    }

    public static Bus getUIBusInstance() {

        return UI_BUS;
    }

    public static RxBus getRxBusInstance() {
        return RX_BUS;
    }
}
