package com.legendmohe.rappid.util;

import android.os.Build;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by legendmohe on 16/6/10.
 */
public class NumberUtil {
    public static int randInt(int min, int max) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        } else {
            Random rand = new Random();
            return rand.nextInt((max - min) + 1) + min;
        }
    }

    public static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    public static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    private static final double PI_DIVIDE_DEGRSS = Math.PI / 180.0;

    public static double degreeToRadian(int degree) {
        return degree * PI_DIVIDE_DEGRSS;
    }

    private static final double DEGREE_DIVIDE_PI = 180.0 / Math.PI;

    public static int radianToDegrss(double radian) {
        return (int) (radian * DEGREE_DIVIDE_PI);
    }

    public static float fraction(float x, float min, float max) {
        min = Math.min(min, max);
        return (x - min) / (max - min);
    }

    public static boolean inRange(int x, float min, float max) {
        min = Math.min(min, max);
        return x >= min && x <= max;
    }

    public static double distanceToCenter(int x, int y, int cx, int cy) {
        return Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
    }

    public static double angle(int ax, int ay, int bx, int by) {
        double sin = ax * by - bx * ay;
        double cos = ax * bx + ay * by;
        return Math.atan2(sin, cos);
    }
}
