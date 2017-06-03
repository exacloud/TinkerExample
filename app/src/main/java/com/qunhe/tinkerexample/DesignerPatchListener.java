/*
 * DesignerPatchListener.java
 * Copyright 2014 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */
package com.qunhe.tinkerexample;

import static com.qunhe.tinkerexample.Config.TINKER_MAX_CRASH_COUNT;
import android.content.Context;

import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author dq
 */
public class DesignerPatchListener extends DefaultPatchListener {
    private static final int ERROR_PATCH_GOOGLE_PLAY_CHANNEL = -6;
    private static final int ERROR_PATCH_CRASH_TOO_MANY_TIMES = -7;
    private Context mContext;

    public DesignerPatchListener(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * 判断是否可以进行热修复
     *
     * @param path 下载后的补丁路径
     * @return errorCode {@link ShareConstants}
     */
    @Override
    public int patchCheck(String path) {
        int returnCode = super.patchCheck(path);

        if (returnCode != ShareConstants.ERROR_PATCH_OK) {
            return returnCode;
        }

        // Google Play渠道的包不进行热修复
        /*if (ActivityUtil.getChannel(mContext).equals(mContext.getString(R.string.ChannelGoogle))) {
            return ERROR_PATCH_GOOGLE_PLAY_CHANNEL;
        }*/

        // 奔溃次数超过上限的不进行热修复
        String currentVersion = SharedPreferencesUtil.
                getString(mContext, SharedPreferencesUtil.CURRENT_PATCH_VERSION);

        int fastCrashCount = SharedPreferencesUtil.getInt(mContext, currentVersion);
        if (fastCrashCount > TINKER_MAX_CRASH_COUNT) {
            return ERROR_PATCH_CRASH_TOO_MANY_TIMES;
        }
        return returnCode;
    }
}
