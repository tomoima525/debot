package com.tomoima.debot

import android.content.Context
import com.tomoima.debot.strategy.*
import java.util.*

object DebotConfigurator {

    @JvmStatic fun configureWithDefault() {
        DebotStrategies.initialize(createDefaultMenuConfig())
    }

    @JvmStatic fun configureWithCustomizedMenu(customList:
    ArrayList<DebotStrategy>) {
        val registerList = createDefaultMenuConfig()
        registerList.addAll(customList)
        DebotStrategies.initialize(registerList)
    }

    private fun createDefaultMenuConfig(): ArrayList<DebotStrategy> {
        val builder = DebotStrategyBuilder.Builder()
                .registerMenu("check dpi", CheckDpiStrategy())
                .registerMenu("showDebugMenu intent", ShowActivityInfoStrategy())
                .registerMenu("check App ver", CheckAppVersionStrategy())
                .registerMenu("dev input", DebotCallActivityMethodStrategy("debugInput"))
                .build()
        return builder.strategyList
    }
}