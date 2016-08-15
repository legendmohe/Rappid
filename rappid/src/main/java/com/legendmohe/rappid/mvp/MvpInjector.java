package com.legendmohe.rappid.mvp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by legendmohe on 16/8/15.
 */
public class MvpInjector {

    public static <T> Set<T> injectPresenter(Object target, Collection<Field> presenterFields) {
        Class<?> clazz = target.getClass();
        Set<T> injectedPresenters = new HashSet<>();
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Field field : presenterFields) {
            Constructor targetConstructor = null;
            Constructor[] constructors = getConstructors(field.getType());
            for (Constructor constructor :
                    constructors) {
                Class[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length != 1)
                    continue;
                boolean found = false;
                for (Class interfaceClass :
                        interfaces) {
                    if (parameterTypes[0].equals(interfaceClass)) {
                        targetConstructor = constructor;
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;
            }
            if (targetConstructor == null) {
                continue;
            }
            try {
                Object presenter = targetConstructor.newInstance(target);
                if (!field.isAccessible())
                    field.setAccessible(true);
                field.set(target, presenter);
                injectedPresenters.add((T) presenter);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return injectedPresenters;
    }

    public static <T> Set<T> injectPresenter(Object target, Class<T> presenterClass) {
        Class<?> clazz = target.getClass();
        Set<Field> presenterFields = getPresenterFields(clazz, MvpPresenter.class, presenterClass);
        return MvpInjector.injectPresenter(target, presenterFields);
    }

    public static Set<Field> getPresenterFields(Class<?> clazz, Class<? extends Annotation> annotationClass, Class<?> presenterClass) {
        Set<Field> fields = new HashSet<>();
        while (!shouldSkipClass(clazz)) {
            final Field[] allFields = clazz.getDeclaredFields();
            for (final Field field : allFields) {
                if (!field.isAnnotationPresent(annotationClass)
                        || Modifier.isStatic(field.getModifiers())
                        || Modifier.isVolatile(field.getModifiers())
                        || !presenterClass.isAssignableFrom(field.getType()))
                    continue;
                fields.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private static Constructor[] getConstructors(Class<?> clazz) {
//        Set<Constructor> constructors = new HashSet<>();
//        while (!shouldSkipClass(clazz)) {
//            final Constructor[] allFields = clazz.getDeclaredConstructors();
//            for (Constructor constructor : allFields) {
//                constructors.add(constructor);
//            }
//            clazz = clazz.getSuperclass();
//        }
//        return constructors.toArray(new Constructor[constructors.size()]);
        return clazz.getConstructors();
    }

    private static boolean shouldSkipClass(final Class<?> clazz) {
        final String clsName = clazz.getName();
        return Object.class.equals(clazz)
                || clsName.startsWith("java.")
                || clsName.startsWith("javax.")
                || clsName.startsWith("android.")
                || clsName.startsWith("com.android.");
    }
}
