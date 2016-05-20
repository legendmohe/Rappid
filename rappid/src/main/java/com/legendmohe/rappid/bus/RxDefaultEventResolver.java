package com.legendmohe.rappid.bus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by legendmohe on 16/4/15.
 */
public class RxDefaultEventResolver implements RxEventHandler.SubscriptionResolver {

    @Override
    public void invokeSubscribeMethod(Object host, Method method, Object event) throws InvocationTargetException, IllegalAccessException {
        method.invoke(host, event);
    }

    @Override
    public Class resolveSubscribedClassFromSourceEvent(Object rawEvent) {
        return rawEvent.getClass();
    }

    @Override
    public Class<? extends Annotation> resolveAnnotationClass() {
        return RxSubscribe.class;
    }

    @Override
    public int resolveParamNum() {
        return 1;
    }
}
