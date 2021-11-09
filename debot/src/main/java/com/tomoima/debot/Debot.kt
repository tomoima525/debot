package com.tomoima.debot


import android.content.Context
import android.hardware.SensorManager
import androidx.fragment.app.FragmentActivity

import com.squareup.seismic.ShakeDetector

import java.lang.ref.WeakReference

class Debot private constructor() {
    private var sd: ShakeDetector? = null
    private var sensorManager: SensorManager? = null
    private var activityWeakRef: WeakReference<FragmentActivity>? = null
    private var canShake: Boolean = false

    fun allowShake(context: Context) {
        canShake = true
        setupSensor(context)
    }

    @JvmOverloads
    fun startSensor(activity: FragmentActivity, sensorDelay: Int = SensorManager.SENSOR_DELAY_GAME) {
        if (!canShake) return
        activityWeakRef = WeakReference(activity)
        sd?.start(sensorManager, sensorDelay)
    }

    fun stopSensor() {
        if (!canShake) return
        activityWeakRef?.clear()
        sd?.stop()
    }

    fun showDebugMenu(activity: FragmentActivity) {
        DebotDialog.get().showDebugMenu(activity)
    }

    private fun setupSensor(context: Context) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sd = ShakeDetector(ShakeDetector.Listener {
            activityWeakRef?.get()?.let(::showDebugMenu)
        })
        sd?.setSensitivity(ShakeDetector.SENSITIVITY_LIGHT)
    }

    companion object {
        @JvmStatic fun getInstance(): Debot = Debot()
    }

}
