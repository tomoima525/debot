package com.tomoima.debot;


import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.tomoima.debot.strategy.CheckDpiStrategy;
import com.tomoima.debot.strategy.DebotStrategy;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DebotConfiguratorTest {

    @Test
    public void testCreateDefaultMenuConfig(){
        ArrayList<DebotStrategy> expected = initArray();

        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        DebotConfigurator configurator = new DebotConfigurator();

        try {
            Method method = DebotConfigurator.class.getDeclaredMethod("createDefaultMenuConfig", Context.class);
            method.setAccessible(true);
            ArrayList<DebotStrategy> actual = (ArrayList<DebotStrategy>) method.invoke(configurator, context);
            assertThat(actual.get(0).getStrategyMenuName(), is(expected.get(0).getStrategyMenuName()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<DebotStrategy> initArray(){
        ArrayList<DebotStrategy> array = new ArrayList<>();
        CheckDpiStrategy checkDpiStrategy = new CheckDpiStrategy();
        checkDpiStrategy.setStrategyMenuName("check dpi");

        array.add(checkDpiStrategy);
        return array;
    }

}
