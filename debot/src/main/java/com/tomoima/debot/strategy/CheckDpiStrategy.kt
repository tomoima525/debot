package com.tomoima.debot.strategy

import android.app.Activity

import com.tomoima.debot.util.DialogUtil

class CheckDpiStrategy : DebotStrategy() {
    private val TITLE = "Density"
    internal enum class Density constructor(val value: Float) {
        MDPI(1.0f),
        HDPI(1.5f),
        XHDPI(2.0f),
        XXHDPI(3.0f),
        TVDPI(213f / 160f)
    }

    override fun startAction(activity: Activity) {

        val density = activity.resources.displayMetrics.density
        val message: String
        if (density == Density.MDPI.value) {
            message = Density.MDPI.name + ": " + density
        } else if (density == Density.HDPI.value) {
            message = Density.HDPI.name + ": " + density
        } else if (density == Density.XHDPI.value) {
            message = Density.XHDPI.name + ": " + density
        } else if (density == Density.XXHDPI.value) {
            message = Density.XXHDPI.name + ": " + density
        } else if (density == Density.TVDPI.value) {
            message = Density.TVDPI.name + ": " + density
        } else {
            message = density.toString()
        }

        DialogUtil.showDialog(activity, TITLE, message)

    }

}
