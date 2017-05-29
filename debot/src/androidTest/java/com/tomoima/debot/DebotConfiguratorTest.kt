package com.tomoima.debot


import com.tomoima.debot.strategy.CheckDpiStrategy
import com.tomoima.debot.strategy.DebotStrategy
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.util.*

class DebotConfiguratorTest {

    @Test
    fun testCreateDefaultMenuConfig() {
        val expected = initArray()

        DebotConfigurator.configureWithDefault()

        val list = DebotStrategies.get()
        assertThat<String>(list[0].strategyMenuName, `is`(expected[0].strategyMenuName))
    }

    private fun initArray(): ArrayList<DebotStrategy> {
        val array = ArrayList<DebotStrategy>()
        val checkDpiStrategy = CheckDpiStrategy()
        checkDpiStrategy.strategyMenuName = "check dpi"

        array.add(checkDpiStrategy)
        return array
    }

}
