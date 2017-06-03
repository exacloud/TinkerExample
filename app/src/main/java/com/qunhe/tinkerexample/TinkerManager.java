package com.qunhe.tinkerexample;

import android.util.Log;

import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.app.ApplicationLike;

public class TinkerManager {
    private static final String TAG = "TinkerManager";
    private static ApplicationLike applicationLike;
    private static TinkerUncaughtExceptionHandler uncaughtExceptionHandler;
    private static boolean isInstalled = false;

    public static void setTinkerApplicationLike(ApplicationLike appLike) {
        applicationLike = appLike;
    }

    public static ApplicationLike getTinkerApplicationLike() {
        return applicationLike;
    }

    public static void initFastCrashProtect() {
        if (uncaughtExceptionHandler == null) {
            uncaughtExceptionHandler = new TinkerUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
    }

    public static void setUpgradeRetryEnable(boolean enable) {
        UpgradePatchRetry.getInstance(applicationLike.getApplication()).setRetryEnable(enable);
    }

    /**
     * install tinker
     *
     * @param appLike Application Like
     */
    public static void installTinker(ApplicationLike appLike) {
        if (isInstalled) {
            Log.w(TAG, "install tinker, but has installed, ignore");
            return;
        }

        TinkerInstaller.install(appLike,
                new DefaultLoadReporter(appLike.getApplication()),
                new DefaultPatchReporter(appLike.getApplication()),
                new DesignerPatchListener(appLike.getApplication()),
                SampleResultService.class,
                new UpgradePatch());

        isInstalled = true;
    }
}
