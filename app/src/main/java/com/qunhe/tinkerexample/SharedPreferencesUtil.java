/*
  SharedPreferencesUtil.java
  Copyright 2016 Qunhe Tech, all rights reserved.
  Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */
package com.qunhe.tinkerexample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class SharedPreferencesUtil {
    public static final String SHARE_PREFERENCES_NAME = "rendershow";

    public static final String CURRENT_PATCH_VERSION = "current_patch_version";
    public static final String TEMP_PATCH_VERSION = "temp_patch_version";

    private static SharedPreferences getSharedPreferences(Context context) {
        if (context != null) {
            return context.getSharedPreferences(SHARE_PREFERENCES_NAME, Activity.MODE_PRIVATE);
        }
        return null;
    }

    public static String getString(final Context context, final String key) {
        return getString(context, key, null);
    }

    public static String getString(final Context context, final String key, final String
            defaultValue) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            return sp.getString(key, defaultValue);
        }
        return null;
    }

    public static void setString(final Context context, final String key, final String value) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            final Editor editor = sp.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static boolean getBoolean(final Context context, final String key, final
    boolean value) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            return sp.getBoolean(key, value);
        }
        return value;
    }

    public static boolean getBoolean(final Context context, final String key) {
        final SharedPreferences sp = getSharedPreferences(context);
        return sp == null || sp.getBoolean(key, true);
    }

    public static void setBoolean(final Context context, final String key, final boolean value) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            final Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public static int getInt(final Context context, final String key) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            return sp.getInt(key, 0);
        }
        return 0;
    }

    public static int getInt(final Context context, final String key, final int defaultValue) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            return sp.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public static void setInt(final Context context, final String key, final int value) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            final Editor editor = sp.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public static long getLong(final Context context, final String key) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            return sp.getLong(key, 0);
        }
        return 0;
    }

    public static void setLong(final Context context, final String key, final long value) {
        final SharedPreferences sp = getSharedPreferences(context);
        if (sp != null) {
            final Editor editor = sp.edit();
            editor.putLong(key, value);
            editor.apply();
        }
    }
}