/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.legendmohe.rappid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.reflect.Field;

/**
 * Created by legendmohe on 15/3/8.
 */
public class PrefUtil {
    public synchronized static String getStringValue(Context context, String key, String def) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, def);
    }

    public synchronized static void setStringValue(Context context, String key, String val) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(key, val);
        prefEditor.apply();
    }

    public synchronized static int getIntValue(Context context, String key, int def) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, def);
    }

    public synchronized static void setIntValue(Context context, String key, int val) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putInt(key, val);
        prefEditor.apply();
    }

    public synchronized static void setLongValue(Context context, String key, long val) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putLong(key, val);
        prefEditor.apply();
    }

    public synchronized static long getLongValue(Context context, String key, long def) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getLong(key, def);
    }

    public synchronized static boolean getBooleanValue(Context context, String key, boolean def) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, def);
    }

    public synchronized static void setBooleanValue(Context context, String key, boolean val) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putBoolean(key, val);
        prefEditor.apply();
    }

    public synchronized static void clear(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.clear();
        prefEditor.apply();
    }

    public synchronized static void clear(Context context, Class constantClass, String prefix) {
        Field[] fields = constantClass.getDeclaredFields();
        if (fields == null || fields.length == 0)
            return;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        for (Field field : fields) {
            if (field.getName().startsWith(prefix) && field.getType().equals(String.class)) {
                try {
                    prefEditor.remove((String) field.get(context));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        prefEditor.apply();
    }
}
