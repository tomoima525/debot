package com.tomoima.debot.strategy;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tomoima.debot.util.DialogUtil;

public class CheckAppVersionStrategy extends DebotStrategy{
    final static String TAG = CheckAppVersionStrategy.class.getSimpleName();
    @Override
    public void startAction(@NonNull Activity activity) {

        String versionName = "";
        int versionCode = 0;
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "can't get package info", e);
        }

        DialogUtil.showDialog(activity, "App Version",
                "VERSION NAME: " + versionName
                + "\nVERSION CODE " + versionCode);
    }
}
