package com.tomoima.debot


import android.content.Context
import android.hardware.SensorManager
import android.support.v4.app.FragmentActivity

import com.squareup.seismic.ShakeDetector

import java.lang.ref.WeakReference

class Debot private constructor() {
    private val debotDialog: DebotDialog = DebotDialog.get()
    private var sd: ShakeDetector? = null
    private var sensorManager: SensorManager? = null
    private var activityWeakRef: WeakReference<FragmentActivity>? = null
    private var canShake: Boolean = false

    fun allowShake(context: Context) {
        canShake = true
        setupSensor(context)
    }

    fun startSensor(activity: FragmentActivity) {
        if (!canShake) return
        activityWeakRef = WeakReference(activity)
        sd?.start(sensorManager);
    }

    fun stopSensor() {
        if (!canShake) return
        activityWeakRef?.clear()
        sd?.stop()
    }

    fun showDebugMenu(activity: FragmentActivity?) {
        debotDialog.showDebugMenu(activity)
    }

    private fun setupSensor(context: Context) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sd = ShakeDetector(ShakeDetector.Listener {
            showDebugMenu(activityWeakRef?.get())
        })
        sd?.setSensitivity(ShakeDetector.SENSITIVITY_LIGHT)
    }

    companion object {
        @JvmStatic fun getInstance(): Debot = Debot()
    }

}
