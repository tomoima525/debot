package com.tomoima.debot.strategy


import android.app.Activity

import java.io.Serializable

abstract class DebotStrategy : Serializable {

    var strategyMenuName: String? = null

    abstract fun startAction(activity: Activity)
}
