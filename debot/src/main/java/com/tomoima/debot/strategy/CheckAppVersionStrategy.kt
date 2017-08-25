package com.tomoima.debot.strategy

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import com.tomoima.debot.util.DialogUtil

class CheckAppVersionStrategy : DebotStrategy() {
    private val TAG = CheckAppVersionStrategy::class.java.simpleName
    override fun startAction(activity: Activity) {

        var versionName = ""
        var versionCode = 0
        try {
            val packageInfo = activity.packageManager.getPackageInfo(activity.packageName, PackageManager.GET_META_DATA)
            versionName = packageInfo.versionName
            versionCode = packageInfo.versionCode

        } catch (e: PackageManager.NameNotFoundException) {
            Log.d(TAG, "can't get package info", e)
        }

        DialogUtil.showDialog(activity, "App Version",
                "VERSION NAME: " + versionName
                        + "\nVERSION CODE " + versionCode)
    }
}
