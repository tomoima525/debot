package com.tomoima.debot

import com.tomoima.debot.strategy.DebotStrategy
import java.util.*

class DebotStrategyBuilder internal constructor(val strategyList: ArrayList<DebotStrategy>) {

    class Builder {
        private var strategyList: ArrayList<DebotStrategy> = ArrayList()

        fun registerMenu(strategyName: String, strategy: DebotStrategy): Builder {
            strategy.strategyMenuName = strategyName
            strategyList.add(strategy)
            return this
        }

        fun build(): DebotStrategyBuilder {
            return DebotStrategyBuilder(strategyList)
        }
    }
}
