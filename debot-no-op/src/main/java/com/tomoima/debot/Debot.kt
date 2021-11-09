package com.tomoima.debot

import android.content.Context
import androidx.fragment.app.FragmentActivity

class Debot private constructor()//Do nothing
{

    fun allowShake(context: Context) {}

    fun startSensor(activity: FragmentActivity, sensorDelay: Int) {}

    fun stopSensor() {}

    fun showDebugMenu(activity: FragmentActivity?) {}

    companion object {

        @JvmStatic fun getInstance(): Debot = Debot()
    }

}
