package com.tomoima.debot

import com.tomoima.debot.strategy.DebotStrategy
import java.util.*

class DebotStrategyBuilder internal constructor(val strategyList: ArrayList<DebotStrategy>) {

    class Builder {
        private val strategyList: ArrayList<DebotStrategy> = ArrayList<DebotStrategy>()

        fun registerMenu(strategyName: String, strategy: DebotStrategy): Builder {
            return this
        }

        fun build(): DebotStrategyBuilder {
            return DebotStrategyBuilder(strategyList)
        }
    }
}
