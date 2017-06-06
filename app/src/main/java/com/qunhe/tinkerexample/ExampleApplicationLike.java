package com.qunhe.tinkerexample;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * @author dq
 */
@SuppressWarnings("unused")
public class ExampleApplicationLike extends DefaultApplicationLike {
    private final static String TAG = "ExampleApplicationLike";
    private int mStartStopPair = 0;
    public ExampleApplicationLike(final Application application, final int tinkerFlags, final
    boolean tinkerLoadVerifyFlag, final long applicationStartElapsedTime, final long
            applicationStartMillisTime, final Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(final Activity activity, final Bundle
                    savedInstanceState) {
                Log.e("pengtao", "onActivityCreated");
            }

            @Override
            public void onActivityStarted(final Activity activity) {
                Log.e("pengtao", "onActivityStarted");
                ++mStartStopPair;
            }

            @Override
            public void onActivityResumed(final Activity activity) {

            }

            @Override
            public void onActivityPaused(final Activity activity) {
                Log.e("pengtao", "onActivityPaused");
            }

            @Override
            public void onActivityStopped(final Activity activity) {
                Log.e("pengtao", "onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(final Activity activity, final Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(final Activity activity) {

            }
        });
    }

    @Override
    public void onBaseContextAttached(final Context base) {
        super.onBaseContextAttached(base);
        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();
        TinkerManager.installTinker(this);
    }

    /**
     * you can restart your process through service or broadcast
     */
    private void restartProcess() {
        TinkerLog.i(TAG, "app is background now, i can kill quietly");
        //you can send service or broadcast intent to restart your process
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
