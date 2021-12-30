package com.tomoima.debot

import android.content.Context
import androidx.fragment.app.FragmentActivity

class Debot private constructor()//Do nothing
{

    fun allowShake(context: Context) {}

    @JvmOverloads
    fun startSensor(activity: FragmentActivity, sensorDelay: Int = 0) {}

    fun stopSensor() {}

    fun showDebugMenu(activity: FragmentActivity?) {}

    companion object {

        @JvmStatic fun getInstance(): Debot = Debot()
    }

}
