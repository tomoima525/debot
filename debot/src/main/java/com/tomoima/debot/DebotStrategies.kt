package com.tomoima.debot

import com.tomoima.debot.strategy.DebotStrategy
import java.util.*

class DebotStrategies {

    companion object {
        private var strategies = ArrayList<DebotStrategy>()

        fun initialize(strategies: ArrayList<DebotStrategy>) {
            this.strategies = strategies
        }

        fun get(): ArrayList<DebotStrategy> {
            return strategies
        }
    }
}
