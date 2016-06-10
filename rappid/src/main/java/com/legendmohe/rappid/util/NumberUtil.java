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
}
