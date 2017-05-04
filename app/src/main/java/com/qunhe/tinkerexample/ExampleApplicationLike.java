package com.qunhe.tinkerexample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * @author dq
 */
@SuppressWarnings("unused")
public class ExampleApplicationLike extends DefaultApplicationLike {

    public ExampleApplicationLike(final Application application, final int tinkerFlags, final
    boolean tinkerLoadVerifyFlag, final long applicationStartElapsedTime, final long
            applicationStartMillisTime, final Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(final Context base) {
        super.onBaseContextAttached(base);
        TinkerInstaller.install(this);
    }
}
