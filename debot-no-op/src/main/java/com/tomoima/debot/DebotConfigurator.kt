package com.tomoima.debot

import android.content.Context
import com.tomoima.debot.strategy.DebotStrategy
import java.util.*

object DebotConfigurator {

    @JvmStatic fun configureWithDefault() {

    }

    @JvmStatic fun configureWithCustomizedMenu(customList: ArrayList<DebotStrategy>) {

    }

    private fun createDefaultMenuConfig(context: Context): ArrayList<DebotStrategy> {
        return ArrayList()
    }
}
