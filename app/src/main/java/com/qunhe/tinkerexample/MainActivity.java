package com.qunhe.tinkerexample;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("pengtao", "patched");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    public void load(View view) {
        String patchPath = getCacheDir() + "/patch_signed.apk";
        Log.e("pengtao", "path = " + patchPath);
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchPath);
    }
}
