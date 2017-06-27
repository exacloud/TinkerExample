package com.qunhe.tinkerexample;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String a = null;
        //Log.e("pengtao", "patched" + a.isEmpty());
        Log.e("pengtao", "patched");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    public void load(View view) {
        String patchPath = "/sdcard/patch_signed.apk";
        Log.e("pengtao", "path = " + patchPath);
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchPath);
    }

    public void getPatchVersion(View view) {
        if (Tinker.isTinkerInstalled()) {
            TinkerLoadResult tinkerLoadResult =
                    Tinker.with(getApplicationContext()).getTinkerLoadResultIfPresent();
            Log.e("pengtao", "currentversion = " + tinkerLoadResult.currentVersion);
        }
    }

    public void cleanPatch(View view) {
        if (Tinker.isTinkerInstalled()) {
            ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
            TinkerInstaller.cleanPatch(this);
        }
    }

    public void isLoaded(View view) {
        Toast.makeText(this, "isLoad = " + Tinker.with(this).isTinkerLoaded(), Toast.LENGTH_SHORT).show();
    }
}
