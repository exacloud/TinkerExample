package com.qunhe.tinkerexample;

import static com.qunhe.tinkerexample.Config.TINKER_MAX_CRASH_COUNT;
import android.util.Log;

import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;
import com.tencent.tinker.loader.app.ApplicationLike;

import java.util.Locale;

/**
 * @author dq
 */
public class TinkerUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final static String TAG = "Tinker.TinkerUncaught";
    private final Thread.UncaughtExceptionHandler mExceptionHandler;

    public TinkerUncaughtExceptionHandler() {
        mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(TAG, "uncaught", ex);
        tinkerFastCrashProtect();
        mExceptionHandler.uncaughtException(thread, ex);
    }

    private boolean tinkerFastCrashProtect() {
        ApplicationLike applicationLike = TinkerManager.getTinkerApplicationLike();
        Log.i(TAG, "tinkerFastCrashProtect");
        if (applicationLike == null || applicationLike.getApplication() == null) {
            return false;
        }
        Log.i(TAG, "isTinkerLoadSuccess");
        if (!TinkerApplicationHelper.isTinkerLoadSuccess(applicationLike)) {
            return false;
        }
        Log.i(TAG, "CURRENT_PATCH_VERSION");
        String currentVersion = SharedPreferencesUtil.getString(applicationLike.getApplication()
                , SharedPreferencesUtil.CURRENT_PATCH_VERSION);
        Log.i(TAG, "CURRENT_PATCH_VERSION = " + currentVersion);
        if (currentVersion == null || currentVersion.isEmpty()) {
            return false;
        }

        int fastCrashCount = SharedPreferencesUtil.
                getInt(applicationLike.getApplication(), currentVersion) + 1;
        SharedPreferencesUtil.setInt(applicationLike.getApplication()
                , currentVersion, fastCrashCount);
        Log.i(TAG, "fastCrashCount = " + fastCrashCount);
        if (fastCrashCount > TINKER_MAX_CRASH_COUNT) {
            TinkerApplicationHelper.cleanPatch(applicationLike);
            Log.e(TAG, String.format(Locale.US, "tinker has fast crash more than %d, we just " +
                    "clean patch!", fastCrashCount));
            return true;
        } else {
            Log.e(TAG, String.format(Locale.US, "tinker has fast crash %d times", fastCrashCount));
        }

        return false;
    }
}
